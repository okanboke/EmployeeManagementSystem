package com.employeemanagementsystem.projectx.service;

import com.employeemanagementsystem.projectx.entity.JustificationPermission;
import com.employeemanagementsystem.projectx.request.JustificationCreateRequest;
import com.employeemanagementsystem.projectx.request.UpdatePermissionRequest;
import com.employeemanagementsystem.projectx.response.JustificationPerResponse;

import java.util.List;

public interface JustificationService {
    JustificationPermission createOneLeave(JustificationCreateRequest leaveCreateRequest);

    List<JustificationPerResponse> getAllJustificationPermission();

    JustificationPermission updatePermissionStatus(UpdatePermissionRequest updatePermissionRequest);

    List<JustificationPerResponse> getOneUserPermissions(Long userId);
}
