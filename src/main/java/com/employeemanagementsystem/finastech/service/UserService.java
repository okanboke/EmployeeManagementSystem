package com.employeemanagementsystem.finastech.service;

import com.employeemanagementsystem.finastech.entity.User;
import com.employeemanagementsystem.finastech.dto.RegistrationDto;
import com.employeemanagementsystem.finastech.request.UserRequest;
import com.employeemanagementsystem.finastech.response.AllUserResponse;
import com.employeemanagementsystem.finastech.response.UserInfoResponse;

import java.util.List;

public interface UserService {

    User createUser(User user);

    //bütün kullanıcılar
    List<AllUserResponse> getAllUsers();

    User getOneUserById(Long userId);

    //Teddy saveUser
    void saveUser(RegistrationDto registrationDto);

    UserInfoResponse getOneUserInfoById(UserRequest userInfoRequest);
}
