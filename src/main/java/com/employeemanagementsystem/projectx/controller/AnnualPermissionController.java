package com.employeemanagementsystem.projectx.controller;

import com.employeemanagementsystem.projectx.entity.AnnualPermission;
import com.employeemanagementsystem.projectx.exception.AnnualExpception;
import com.employeemanagementsystem.projectx.request.*;
import com.employeemanagementsystem.projectx.response.AnnualCalcResponse;
import com.employeemanagementsystem.projectx.response.AnnualPermissionResponse;
import com.employeemanagementsystem.projectx.service.impl.AnnualPermissionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/annual/permissions")
public class AnnualPermissionController {

    private final AnnualPermissionServiceImpl annualPermissionService;

    public AnnualPermissionController(AnnualPermissionServiceImpl annualPermissionService) {
        this.annualPermissionService = annualPermissionService;
    }

    //for Admin Yıllık izin listeleme
    @GetMapping("/admin/list-annual") //Role Admin
    public List<AnnualPermissionResponse> getAllAnnualPermissions() {
        return annualPermissionService.getAllAnnualPermissions();
    }

    //for user izin görüntüleme
    @PostMapping("/user/list-permissions")
    public ResponseEntity<List<AnnualPermissionResponse>> getOneUserPermissions(@RequestBody UserRequest userRequest) {
        //return justificationService.getOneUserPermissions(userId);
        List<AnnualPermissionResponse> list = annualPermissionService.getUserAnnualPermissions(userRequest.getId());
        return ResponseEntity.ok(list);
    }

    //for admin izin onayı
    @PutMapping("/admin/update-status")
    public AnnualPermission updatePermissionStatus(
            @RequestBody UpdateAnnualRequest updatePermissionRequest) {
        return annualPermissionService.updatePermissionStatus(updatePermissionRequest);
    }

    //for User yıllık izin talebi
    @PostMapping("/user/create")
    public ResponseEntity<AnnualPermission> createOneLeave(
            @RequestBody AnnualCreateRequest annualCreateRequest) {
        AnnualPermission model = new AnnualPermission();
        try {
            model = annualPermissionService.createPermission(annualCreateRequest);
            return new ResponseEntity<>(model, HttpStatus.OK);
        } catch (AnnualExpception ex) {
            model.setErrorMessage(ex.getMessage());
            return new ResponseEntity<>(model, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    //bff ve fronend'e eklenecek
    //for User yıllık izin günü hesaplama
    @PostMapping("/user/calculate")
    public ResponseEntity<AnnualCalcResponse> annualCalculate(
            @RequestBody AnnualCalcRequest annualCalcRequest) {
        AnnualCalcResponse calculator = annualPermissionService.annualCalculate(annualCalcRequest);
        return new ResponseEntity<>(calculator, HttpStatus.CREATED);


    }
}
