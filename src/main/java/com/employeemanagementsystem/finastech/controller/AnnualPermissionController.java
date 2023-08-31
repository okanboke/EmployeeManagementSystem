package com.employeemanagementsystem.finastech.controller;

import com.employeemanagementsystem.finastech.entity.AnnualPermission;
import com.employeemanagementsystem.finastech.entity.JustificationPermission;
import com.employeemanagementsystem.finastech.request.AnnualCreateRequest;
import com.employeemanagementsystem.finastech.request.JustificationCreateRequest;
import com.employeemanagementsystem.finastech.request.UserRequest;
import com.employeemanagementsystem.finastech.response.AnnualPermissionResponse;
import com.employeemanagementsystem.finastech.response.JustificationPerResponse;
import com.employeemanagementsystem.finastech.service.impl.AnnualPermissionServiceImpl;
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
    public List<AnnualPermissionResponse> getAllAnnualPermissions(){
        return annualPermissionService.getAllAnnualPermissions();
    }

    //for user izin görüntüleme
    @PostMapping("/user/list-permissions")
    public ResponseEntity<List<AnnualPermissionResponse>> getOneUserPermissions(@RequestBody UserRequest userRequest){
        //return justificationService.getOneUserPermissions(userId);
        List<AnnualPermissionResponse> list = annualPermissionService.getUserAnnualPermissions(userRequest.getId());
        return ResponseEntity.ok(list);
    }

    //for User yıllık izin talebi
    @PostMapping("/user/create")
    public ResponseEntity<AnnualPermission> createOneLeave(
            @RequestBody AnnualCreateRequest annualCreateRequest) {
        AnnualPermission list = annualPermissionService.createPermission(annualCreateRequest);
        return ResponseEntity.ok(list);
    }
}
