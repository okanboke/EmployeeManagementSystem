package com.employeemanagementsystem.finastech.service;

import com.employeemanagementsystem.finastech.entity.User;
import com.employeemanagementsystem.finastech.dto.RegistrationDto;
import com.employeemanagementsystem.finastech.request.CreateProfileRequest;
import com.employeemanagementsystem.finastech.response.AllUserResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    User createUser(User user);

    List<AllUserResponse> getAllUsers();

    User getOneUserById(Long userId);

    //Teddy saveUser
    void saveUser(RegistrationDto registrationDto);

}
