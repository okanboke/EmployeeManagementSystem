package com.employeemanagementsystem.finastech.service;

import com.employeemanagementsystem.finastech.entity.User;
import com.employeemanagementsystem.finastech.dto.RegistrationDto;

import java.util.List;

public interface UserService {

    User createUser(User user);

    List<User> getAllUsers();

    User getOneUserById(Long userId);

    //Teddy saveUser
    void saveUser(RegistrationDto registrationDto);

}
