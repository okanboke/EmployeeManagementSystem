package com.employeemanagementsystem.finastech.controller;

import com.employeemanagementsystem.finastech.entity.AnnualPermission;
import com.employeemanagementsystem.finastech.entity.JustificationPermission;
import com.employeemanagementsystem.finastech.exception.AnnualExpception;
import com.employeemanagementsystem.finastech.request.AnnualCreateRequest;
import com.employeemanagementsystem.finastech.request.UpdateAnnualRequest;
import com.employeemanagementsystem.finastech.request.UpdatePermissionRequest;
import com.employeemanagementsystem.finastech.request.UserRequest;
import com.employeemanagementsystem.finastech.response.AnnualPermissionResponse;
import com.employeemanagementsystem.finastech.response.AnnualPermissionResponseModel;
import com.employeemanagementsystem.finastech.service.impl.AnnualPermissionServiceImpl;
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
        }
        catch (AnnualExpception ex) {
            model.setErrorMessage(ex.getMessage());
            return new ResponseEntity<>(model, HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
