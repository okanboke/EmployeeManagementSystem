package com.employeemanagementsystem.projectx.service.impl;

import com.employeemanagementsystem.projectx.entity.Role;
import com.employeemanagementsystem.projectx.repository.RoleRepository;
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
