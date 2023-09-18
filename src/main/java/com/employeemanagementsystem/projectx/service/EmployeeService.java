package com.employeemanagementsystem.projectx.service;

import com.employeemanagementsystem.projectx.entity.Address;
import com.employeemanagementsystem.projectx.request.CreateProfileRequest;

public interface EmployeeService {


    Address createProfile(CreateProfileRequest createProfileRequest);

    Address updateAddress(CreateProfileRequest createProfileRequest);

    Address getOneByAddressId(Long addressId);

    Address getOneByAddressUserId(Long userId);


}
