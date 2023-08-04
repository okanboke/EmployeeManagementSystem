package com.employeemanagementsystem.finastech.service.impl;

import com.employeemanagementsystem.finastech.entity.Address;
import com.employeemanagementsystem.finastech.entity.User;
import com.employeemanagementsystem.finastech.repository.EmployeeRepository;
import com.employeemanagementsystem.finastech.request.CreateProfileRequest;
import com.employeemanagementsystem.finastech.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private UserServiceImpl userService;
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(UserServiceImpl userService,
                               EmployeeRepository employeeRepository) { //Constructor Injection
        this.userService = userService;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Address createProfile(CreateProfileRequest createProfileRequest) {
        User user = userService.getOneUserById(createProfileRequest.getId()); //user kontrolü yapıp post ekleyeceğiz
        if (user == null)
            return null;

        Address toSave = new Address();

        toSave.setAddressId(createProfileRequest.getId());
        toSave.setStreet(createProfileRequest.getStreet());
        toSave.setDistrict(createProfileRequest.getDistrict());
        toSave.setCity(createProfileRequest.getCity());
        toSave.setCountry(createProfileRequest.getCountry());
        toSave.setApartmentNumber(createProfileRequest.getApartmentNumber());
        toSave.setDoorNumber(createProfileRequest.getDoorNumber());
        toSave.setUser(user);
        return employeeRepository.save(toSave);
    }
}
