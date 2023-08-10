package com.employeemanagementsystem.finastech.controller;

import com.employeemanagementsystem.finastech.entity.JustificationPermission;
import com.employeemanagementsystem.finastech.request.JustificationCreateRequest;
import com.employeemanagementsystem.finastech.response.AllUserResponse;
import com.employeemanagementsystem.finastech.service.impl.JustificationServiceImpl;
import com.employeemanagementsystem.finastech.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping("/create") //role User
    public JustificationPermission createOneLeave(
            @RequestBody JustificationCreateRequest leaveCreateRequest) { //yeni bir ürün ekleme
        return justificationService.createOneLeave(leaveCreateRequest/*,categories*/);
    }
}