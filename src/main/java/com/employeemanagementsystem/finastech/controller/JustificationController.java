package com.employeemanagementsystem.finastech.controller;

import com.employeemanagementsystem.finastech.entity.Address;
import com.employeemanagementsystem.finastech.entity.JustificationPermission;
import com.employeemanagementsystem.finastech.request.CreateProfileRequest;
import com.employeemanagementsystem.finastech.request.JustificationCreateRequest;
import com.employeemanagementsystem.finastech.request.UpdatePermissionRequest;
import com.employeemanagementsystem.finastech.service.impl.JustificationServiceImpl;
import com.employeemanagementsystem.finastech.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permissions")
public class JustificationController {


    private final JustificationServiceImpl justificationService;

    public JustificationController(JustificationServiceImpl justificationService) {
        this.justificationService = justificationService;
    }

    //for admin izin onayı
    @PutMapping("/admin/update-status")
    public JustificationPermission updatePermissionStatus(
            @RequestBody UpdatePermissionRequest updatePermissionRequest) {
        return justificationService.updatePermissionStatus(updatePermissionRequest);
    }

    // izin isteği
    @PostMapping("/create") //role User
    public JustificationPermission createOneLeave(
            @RequestBody JustificationCreateRequest leaveCreateRequest) { //yeni bir ürün ekleme
        return justificationService.createOneLeave(leaveCreateRequest/*,categories*/);
    }
}