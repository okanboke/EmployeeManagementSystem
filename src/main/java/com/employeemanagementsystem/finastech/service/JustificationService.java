package com.employeemanagementsystem.finastech.service;

import com.employeemanagementsystem.finastech.entity.JustificationPermission;
import com.employeemanagementsystem.finastech.request.JustificationCreateRequest;
import com.employeemanagementsystem.finastech.request.UpdatePermissionRequest;
import com.employeemanagementsystem.finastech.response.JustificationPerResponse;

import java.util.List;
import java.util.Optional;

public interface JustificationService {
    JustificationPermission createOneLeave(JustificationCreateRequest leaveCreateRequest);

    List<JustificationPerResponse> getAllJustificationPermission();

    JustificationPermission updatePermissionStatus(UpdatePermissionRequest updatePermissionRequest);

    List<JustificationPerResponse> getOneUserPermissions(Long userId);
}
