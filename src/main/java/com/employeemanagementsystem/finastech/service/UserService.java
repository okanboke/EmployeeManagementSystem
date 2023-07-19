package com.employeemanagementsystem.finastech.service;

import com.employeemanagementsystem.finastech.entity.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    List<User> getAllUsers();

    User getOneUserById(Long userId);

}
