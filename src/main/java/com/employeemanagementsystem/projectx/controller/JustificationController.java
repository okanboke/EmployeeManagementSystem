package com.employeemanagementsystem.projectx.controller;

import com.employeemanagementsystem.projectx.entity.JustificationPermission;
import com.employeemanagementsystem.projectx.request.*;
import com.employeemanagementsystem.projectx.response.JustificationPerResponse;
import com.employeemanagementsystem.projectx.service.impl.JustificationServiceImpl;
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

    //for user izin görüntüleme
    @PostMapping("/user/list-permissions")
    public ResponseEntity<List<JustificationPerResponse>> getOneUserPermissions
    (@RequestBody UserRequest userRequest) {
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