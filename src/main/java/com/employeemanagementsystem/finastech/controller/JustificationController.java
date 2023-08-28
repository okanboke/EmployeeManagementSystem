package com.employeemanagementsystem.finastech.controller;

import com.employeemanagementsystem.finastech.entity.JustificationPermission;
import com.employeemanagementsystem.finastech.request.*;
import com.employeemanagementsystem.finastech.response.JustificationPerResponse;
import com.employeemanagementsystem.finastech.service.impl.JustificationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class JustificationController {

    private final JustificationServiceImpl justificationService;

    public JustificationController(JustificationServiceImpl justificationService) {
        this.justificationService = justificationService;
    }

    //for user spefisik izin görüntüleme
    @PostMapping("/user/list-permissions")
    public ResponseEntity<List<JustificationPerResponse>> getOneUserPermissions(@RequestBody UserRequest userRequest){
        //return justificationService.getOneUserPermissions(userId);
        List<JustificationPerResponse> list = justificationService.getOneUserPermissions(userRequest.getId());
        return ResponseEntity.ok(list);
    }

    //for admin izin onayı
    @PutMapping("/admin/update-status")
    public JustificationPermission updatePermissionStatus(
            @RequestBody UpdatePermissionRequest updatePermissionRequest) {
        return justificationService.updatePermissionStatus(updatePermissionRequest);
    }

    // izin isteği
    @PostMapping("/create") //role User
    public ResponseEntity<JustificationPermission> createOneLeave(
            @RequestBody JustificationCreateRequest leaveCreateRequest) { //yeni bir izin ekleme
        JustificationPermission list = justificationService.createOneLeave(leaveCreateRequest);
        return ResponseEntity.ok(list);
    }
}