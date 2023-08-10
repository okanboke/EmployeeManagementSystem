package com.employeemanagementsystem.finastech.service;

import com.employeemanagementsystem.finastech.entity.JustificationPermission;
import com.employeemanagementsystem.finastech.request.JustificationCreateRequest;
import com.employeemanagementsystem.finastech.response.AllUserResponse;
import com.employeemanagementsystem.finastech.response.JustificationPerResponse;

import java.util.List;

public interface JustificationService {
    JustificationPermission createOneLeave(JustificationCreateRequest leaveCreateRequest);

    List<JustificationPerResponse> getAllJustificationPermission();
}
