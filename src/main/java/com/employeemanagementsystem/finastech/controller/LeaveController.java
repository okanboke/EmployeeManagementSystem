package com.employeemanagementsystem.finastech.controller;

import com.employeemanagementsystem.finastech.entity.Leaves;
import com.employeemanagementsystem.finastech.request.LeaveCreateRequest;
import com.employeemanagementsystem.finastech.service.impl.LeaveServiceImpl;
import com.employeemanagementsystem.finastech.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    private final UserServiceImpl userService;

    private final LeaveServiceImpl leaveService;

    public LeaveController(UserServiceImpl userService,
                           LeaveServiceImpl leaveService) {
        this.userService = userService;
        this.leaveService = leaveService;
    }

    // izin isteği
    @PostMapping("/create")
    public Leaves createOneLeave(
            @RequestBody LeaveCreateRequest leaveCreateRequest
            /*,
            @ModelAttribute String categories*/) { //yeni bir ürün ekleme
        return leaveService.createOneLeave(leaveCreateRequest/*,categories*/);
    }
}