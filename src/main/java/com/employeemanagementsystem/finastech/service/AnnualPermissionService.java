package com.employeemanagementsystem.finastech.service;

import com.employeemanagementsystem.finastech.entity.AnnualPermission;
import com.employeemanagementsystem.finastech.request.AnnualCalcRequest;
import com.employeemanagementsystem.finastech.request.AnnualCreateRequest;
import com.employeemanagementsystem.finastech.request.UpdateAnnualRequest;
import com.employeemanagementsystem.finastech.response.AnnualCalcResponse;
import com.employeemanagementsystem.finastech.response.AnnualPermissionResponse;

import java.util.List;

public interface AnnualPermissionService {
    void updateRestDaysForUsers();

    AnnualPermission createPermission(AnnualCreateRequest annualCreateRequest);

    List<AnnualPermissionResponse> getUserAnnualPermissions(Long id);

    List<AnnualPermissionResponse> getAllAnnualPermissions();

    AnnualPermission updatePermissionStatus(UpdateAnnualRequest updatePermissionRequest);

    AnnualCalcResponse annualCalculate(AnnualCalcRequest annualCalcRequest);
}
