package com.employeemanagementsystem.finastech.controller;

import com.employeemanagementsystem.finastech.service.impl.AnnualPermissionServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/annual")
public class AnnualPermissionController {

    private final AnnualPermissionServiceImpl annualPermissionService;

    public AnnualPermissionController(AnnualPermissionServiceImpl annualPermissionService) {
        this.annualPermissionService = annualPermissionService;
    }
}
