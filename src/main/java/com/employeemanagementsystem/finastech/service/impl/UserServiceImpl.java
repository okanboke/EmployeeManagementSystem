package com.employeemanagementsystem.finastech.service.impl;

import com.employeemanagementsystem.finastech.entity.User;
import com.employeemanagementsystem.finastech.repository.UserRepository;
import com.employeemanagementsystem.finastech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User newUser) { //User kaydı oluşturur
        return userRepository.save(newUser);
    }

    @Override
    public List<User> getAllUsers() { //tüm kullanıcılar listelenecek
        return userRepository.findAll();
    }

    @Override
    public User getOneUserById(Long userId) { //spesifik bir kullanıcı listelenecek
        return userRepository.findById(userId).orElse(null);
    }

    public User getOneUserByUserName(String userName) { //database'e gidip böyle bir user var mı yok mu onu kontrol edeceğiz.
        return userRepository.findByUserName(userName);
    }
}
