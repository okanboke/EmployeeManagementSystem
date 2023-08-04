package com.employeemanagementsystem.finastech.controller;

import com.employeemanagementsystem.finastech.entity.Address;
import com.employeemanagementsystem.finastech.request.CreateProfileRequest;
import com.employeemanagementsystem.finastech.service.impl.EmployeeServiceImpl;
import com.employeemanagementsystem.finastech.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) { //Constructor Injection
        this.employeeService = employeeService;
    }

    //Çalışan adres ve profil bilgileri ekleme
    @PostMapping("/edit-profile")
    public Address createProfile(
            @RequestBody CreateProfileRequest createProfileRequest) {
        return employeeService.createProfile(createProfileRequest);
    }
}
