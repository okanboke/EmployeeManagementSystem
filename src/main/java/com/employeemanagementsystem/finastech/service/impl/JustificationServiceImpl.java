package com.employeemanagementsystem.finastech.service.impl;

import com.employeemanagementsystem.finastech.entity.JustificationPermission;
import com.employeemanagementsystem.finastech.entity.User;
import com.employeemanagementsystem.finastech.repository.JustificationRepository;
import com.employeemanagementsystem.finastech.request.JustificationCreateRequest;
import com.employeemanagementsystem.finastech.response.AllUserResponse;
import com.employeemanagementsystem.finastech.response.JustificationPerResponse;
import com.employeemanagementsystem.finastech.service.JustificationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JustificationServiceImpl implements JustificationService {

    private final UserServiceImpl userService;
    private final JustificationRepository justificationRepository;


    public JustificationServiceImpl(UserServiceImpl userService,
                                    JustificationRepository justificationRepository) {
        this.userService = userService;
        this.justificationRepository = justificationRepository;
    }

    @Override
    public JustificationPermission createOneLeave(JustificationCreateRequest justificationCreateRequest) {
        User user = userService.getOneUserById(justificationCreateRequest.getUserId()); //user kontrolü yapıp post ekleyeceğiz

        if(user == null)
            return null;

        JustificationPermission toSave = new JustificationPermission();
        toSave.setPermissionId(justificationCreateRequest.getPermissionId());
        //toSave.setJustPerType(justificationCreateRequest.);
        toSave.setPermissionDescription(justificationCreateRequest.getPermissionDescription());
        toSave.setStartDate(justificationCreateRequest.getStartDate());
        toSave.setEndDate(justificationCreateRequest.getEndDate());
        toSave.setApprovalStatus(justificationCreateRequest.isApprovalStatus());

        toSave.setUser(user);
        return justificationRepository.save(toSave);    }

    //Mazeret İzin isteklerini listeleme
    @Override
    public List<JustificationPerResponse> getAllJustificationPermission() {
        List<JustificationPermission> list;
        list = justificationRepository.findAll();
        return list.stream().map(justification_permission -> new JustificationPerResponse(justification_permission)).collect(Collectors.toList());
    }
}
