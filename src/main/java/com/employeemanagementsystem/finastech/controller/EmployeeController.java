package com.employeemanagementsystem.finastech.controller;

import com.employeemanagementsystem.finastech.entity.Address;
import com.employeemanagementsystem.finastech.entity.User;
import com.employeemanagementsystem.finastech.exception.UserNotFoundException;
import com.employeemanagementsystem.finastech.request.CreateProfileRequest;
import com.employeemanagementsystem.finastech.response.UserResponse;
import com.employeemanagementsystem.finastech.service.impl.EmployeeServiceImpl;
import com.employeemanagementsystem.finastech.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    private final UserServiceImpl userService;

    public EmployeeController(EmployeeServiceImpl employeeService,
                              UserServiceImpl userService) { //Constructor Injection
        this.employeeService = employeeService;
        this.userService = userService;
    }
    @GetMapping("/profile/{userId}")
    public UserResponse getOneUser(@PathVariable Long userId) { //tek bir user çekilmek istendiğinde Service sınıfına gider ve metoduyla gerçekleştirir.
        User user = userService.getOneUserById(userId);

        if(user == null) { //öyle bir user yoksa
            throw new UserNotFoundException();
        }
        return new UserResponse(user);
    }
    //Çalışan adres ve profil bilgileri ekleme
    @PostMapping("/edit-profile")
    public Address createProfile(
            @RequestBody CreateProfileRequest createProfileRequest) {
        return employeeService.createProfile(createProfileRequest);
    }
}
