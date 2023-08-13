package com.employeemanagementsystem.finastech.controller;

import com.employeemanagementsystem.finastech.response.JustPerTypeResponse;
import com.employeemanagementsystem.finastech.service.impl.JustPerTypeServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/permissions/type")
public class JustPerTypeController {

    private final JustPerTypeServiceImpl justPerTypeService;

    public JustPerTypeController(JustPerTypeServiceImpl justPerTypeService) {
        this.justPerTypeService = justPerTypeService;
    }

    //for User Get
    @GetMapping("/list-types")
    public List<JustPerTypeResponse> getAllJustPerType() {
        return justPerTypeService.getAllJustPerType();
    }
}
