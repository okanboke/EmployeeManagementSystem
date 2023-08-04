package com.employeemanagementsystem.finastech.service;

import com.employeemanagementsystem.finastech.entity.Address;
import com.employeemanagementsystem.finastech.request.CreateProfileRequest;
import org.springframework.http.ResponseEntity;

public interface EmployeeService {


    Address createProfile(CreateProfileRequest createProfileRequest);

}
