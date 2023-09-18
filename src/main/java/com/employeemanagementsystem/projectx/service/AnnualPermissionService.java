package com.employeemanagementsystem.projectx.service;

import com.employeemanagementsystem.projectx.entity.AnnualPermission;
import com.employeemanagementsystem.projectx.request.AnnualCalcRequest;
import com.employeemanagementsystem.projectx.request.AnnualCreateRequest;
import com.employeemanagementsystem.projectx.request.UpdateAnnualRequest;
import com.employeemanagementsystem.projectx.response.AnnualCalcResponse;
import com.employeemanagementsystem.projectx.response.AnnualPermissionResponse;

import java.util.List;

public interface AnnualPermissionService {
    void updateRestDaysForUsers();

    AnnualPermission createPermission(AnnualCreateRequest annualCreateRequest);

    List<AnnualPermissionResponse> getUserAnnualPermissions(Long id);

    List<AnnualPermissionResponse> getAllAnnualPermissions();

    AnnualPermission updatePermissionStatus(UpdateAnnualRequest updatePermissionRequest);

    AnnualCalcResponse annualCalculate(AnnualCalcRequest annualCalcRequest);
}
