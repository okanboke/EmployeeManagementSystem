package com.employeemanagementsystem.finastech.controller;

import com.employeemanagementsystem.finastech.entity.Address;
import com.employeemanagementsystem.finastech.entity.JustPerType;
import com.employeemanagementsystem.finastech.request.CreateProfileRequest;
import com.employeemanagementsystem.finastech.response.JustPerTypeResponse;
import com.employeemanagementsystem.finastech.service.impl.JustPerTypeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions/type")
public class JustPerTypeController {

    private final JustPerTypeServiceImpl justPerTypeService;

    public JustPerTypeController(JustPerTypeServiceImpl justPerTypeService) {
        this.justPerTypeService = justPerTypeService;
    }

    //for User Get
    @GetMapping("/user/list-types")
    public List<JustPerTypeResponse> getAllJustPerType() {
        return justPerTypeService.getAllJustPerType();
    }

    //for Admin Post
    @PostMapping("/admin/create-type")
    public ResponseEntity<JustPerType> createJustPerType(@RequestBody JustPerType justPerType) {
        JustPerType createdType = justPerTypeService.createJustPerType(justPerType);
        return new ResponseEntity<>(createdType, HttpStatus.CREATED);

    }



    //for admin izin türü silme
    @DeleteMapping("/admin/{justPerId}")
    public void deleteJustPerType(@PathVariable Long justPerId){
        justPerTypeService.deleteJustPerType(justPerId);
    }
}
