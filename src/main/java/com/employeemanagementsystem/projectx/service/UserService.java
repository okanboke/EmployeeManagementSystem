package com.employeemanagementsystem.projectx.service;

import com.employeemanagementsystem.projectx.entity.User;
import com.employeemanagementsystem.projectx.dto.RegistrationDto;
import com.employeemanagementsystem.projectx.request.UserRequest;
import com.employeemanagementsystem.projectx.response.AllUserResponse;
import com.employeemanagementsystem.projectx.response.UserInfoResponse;

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
