package com.employeemanagementsystem.finastech.controller;

import com.employeemanagementsystem.finastech.entity.Role;
import com.employeemanagementsystem.finastech.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;

    @PostMapping({"/createNewRole"}) //Role ekleme
    public Role createNewRole(@RequestBody Role role) {
        return roleService.createNewRole(role);

    }
}
