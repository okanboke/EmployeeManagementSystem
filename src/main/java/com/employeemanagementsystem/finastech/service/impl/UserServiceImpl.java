package com.employeemanagementsystem.finastech.service.impl;

import com.employeemanagementsystem.finastech.dto.RegistrationDto;
import com.employeemanagementsystem.finastech.entity.Role;
import com.employeemanagementsystem.finastech.entity.User;
import com.employeemanagementsystem.finastech.repository.RoleRepository;
import com.employeemanagementsystem.finastech.repository.UserRepository;
import com.employeemanagementsystem.finastech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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


    //Teddy
    @Override
    public void saveUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setUserName(registrationDto.getUserName());
        user.setPassword(registrationDto.getPassword());
        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
        Role role = roleRepository.findByRoleName("user");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }



}
