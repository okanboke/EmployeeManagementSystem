package com.employeemanagementsystem.finastech.service.impl;

import com.employeemanagementsystem.finastech.entity.Leaves;
import com.employeemanagementsystem.finastech.entity.User;
import com.employeemanagementsystem.finastech.repository.LeaveRepository;
import com.employeemanagementsystem.finastech.request.LeaveCreateRequest;
import com.employeemanagementsystem.finastech.service.LeaveService;
import org.springframework.stereotype.Service;

@Service
public class LeaveServiceImpl implements LeaveService {

    private final UserServiceImpl userService;
    private final LeaveRepository leaveRepository;


    public LeaveServiceImpl(UserServiceImpl userService,
                            LeaveRepository leaveRepository) {
        this.userService = userService;
        this.leaveRepository = leaveRepository;
    }

    @Override
    public Leaves createOneLeave(LeaveCreateRequest leaveCreateRequest) {
        User user = userService.getOneUserById(leaveCreateRequest.getUserId()); //user kontrolü yapıp post ekleyeceğiz

        if(user == null)
            return null;

        Leaves toSave = new Leaves();
        toSave.setLeaveId(leaveCreateRequest.getId());
        toSave.setLeaveType(leaveCreateRequest.getLeaveType());
        toSave.setStartDate(leaveCreateRequest.getStartDate());
        toSave.setEndDate(leaveCreateRequest.getEndDate());
        toSave.setApprovalStatus(leaveCreateRequest.getApprovalStatus());

        toSave.setUser(user);
        return leaveRepository.save(toSave);    }
}
