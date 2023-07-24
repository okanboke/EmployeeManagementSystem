package com.employeemanagementsystem.finastech.service.impl;

import com.employeemanagementsystem.finastech.entity.Role;
import com.employeemanagementsystem.finastech.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl {

    @Autowired
    private RoleRepository roleRepository;

    public Role createNewRole(Role role) {
       return roleRepository.save(role);

    }
}
