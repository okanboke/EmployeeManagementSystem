package com.employeemanagementsystem.finastech.service;

import com.employeemanagementsystem.finastech.entity.JustificationPermission;
import com.employeemanagementsystem.finastech.request.JustificationCreateRequest;

public interface JustificationService {
    JustificationPermission createOneLeave(JustificationCreateRequest leaveCreateRequest);

}
