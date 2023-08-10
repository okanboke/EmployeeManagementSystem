package com.employeemanagementsystem.finastech.controller;

import com.employeemanagementsystem.finastech.entity.JustificationPermission;
import com.employeemanagementsystem.finastech.request.JustificationCreateRequest;
import com.employeemanagementsystem.finastech.service.impl.JustificationServiceImpl;
import com.employeemanagementsystem.finastech.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/permissions")
public class JustificationController {

    private final UserServiceImpl userService;

    private final JustificationServiceImpl justificationService;

    public JustificationController(UserServiceImpl userService,
                                   JustificationServiceImpl justificationService) {
        this.userService = userService;
        this.justificationService = justificationService;
    }

    // izin isteği
    @PostMapping("/create")
    public JustificationPermission createOneLeave(
            @RequestBody JustificationCreateRequest leaveCreateRequest
            /*,
            @ModelAttribute String categories*/) { //yeni bir ürün ekleme
        return justificationService.createOneLeave(leaveCreateRequest/*,categories*/);
    }
}