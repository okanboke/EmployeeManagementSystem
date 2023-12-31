package com.employeemanagementsystem.projectx.service.impl;

import com.employeemanagementsystem.projectx.entity.JustPerType;
import com.employeemanagementsystem.projectx.entity.JustificationPermission;
import com.employeemanagementsystem.projectx.entity.User;
import com.employeemanagementsystem.projectx.repository.JustificationRepository;
import com.employeemanagementsystem.projectx.request.JustificationCreateRequest;
import com.employeemanagementsystem.projectx.request.UpdatePermissionRequest;
import com.employeemanagementsystem.projectx.response.JustificationPerResponse;
import com.employeemanagementsystem.projectx.service.JustificationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JustificationServiceImpl implements JustificationService {

    private final UserServiceImpl userService;
    private final JustificationRepository justificationRepository;

    private final JustPerTypeServiceImpl justPerTypeService;


    public JustificationServiceImpl(UserServiceImpl userService,
                                    JustificationRepository justificationRepository,
                                    JustPerTypeServiceImpl justPerTypeService) {
        this.userService = userService;
        this.justificationRepository = justificationRepository;
        this.justPerTypeService = justPerTypeService;
    }

    @Override
    public JustificationPermission createOneLeave(JustificationCreateRequest justificationCreateRequest) {
        User user = userService.getOneUserById(justificationCreateRequest.getUserId()); //user kontrolü yapıp post ekleyeceğiz

        //İzin tipi tabloya id ile eşleşecek
        JustPerType justPerType = justPerTypeService.getOneJustPerTypeById(justificationCreateRequest.getPermissionTypeId());
        if (user == null && justPerType == null)
            return null;

        JustificationPermission toSave = new JustificationPermission();
        toSave.setPermissionId(justificationCreateRequest.getPermissionId());
        //toSave.getJustPerType().setJustPerId(justificationCreateRequest.getPermissionTypeId());
        toSave.setPermissionDescription(justificationCreateRequest.getPermissionDescription());
        toSave.setStartDate(justificationCreateRequest.getStartDate());
        toSave.setEndDate(justificationCreateRequest.getEndDate());
        toSave.setApprovalStatus(justificationCreateRequest.isApprovalStatus());
        toSave.setErrorMessage("Talebiniz başarıyla gönderildi");

        toSave.setJustPerType(justPerType);
        toSave.setUser(user);
        return justificationRepository.save(toSave);
    }

    //Mazeret İzin isteklerini listeleme Admin
    @Override
    public List<JustificationPerResponse> getAllJustificationPermission() {
        List<JustificationPermission> list;
        list = justificationRepository.findAll();
        return list.stream().map(justification_permission -> new JustificationPerResponse(justification_permission)).collect(Collectors.toList());
    }

    //for admin izin onayı
    //koşul eklenecek
    @Override
    public JustificationPermission updatePermissionStatus(UpdatePermissionRequest updatePermissionRequest) {
        return justificationRepository.findById(updatePermissionRequest.getPermissionId())
                .map(justification_permission -> {
                    justification_permission.setApprovalStatus(updatePermissionRequest.isApprovalStatus());
                    return justificationRepository.save(justification_permission);
                })
                .orElseGet(() -> {
                    return null;
                });
    }

    //userId'ye göre user'ın kendi izinlerini listeleme
    @Override
    public List<JustificationPerResponse> getOneUserPermissions(Long userId) {
        return justificationRepository.findByUser_Id(userId);

    }
}

