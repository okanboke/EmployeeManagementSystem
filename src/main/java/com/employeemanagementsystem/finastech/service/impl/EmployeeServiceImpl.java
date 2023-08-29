package com.employeemanagementsystem.finastech.service.impl;

import com.employeemanagementsystem.finastech.entity.Address;
import com.employeemanagementsystem.finastech.entity.User;
import com.employeemanagementsystem.finastech.repository.EmployeeRepository;
import com.employeemanagementsystem.finastech.request.CreateProfileRequest;
import com.employeemanagementsystem.finastech.service.EmployeeService;
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
    //spesifik adres listeleme
    @Override //adressId listeleme
    public Address getOneByAddressUserId(Long userId) { //spesifik bir kullanıcı listelenecek
        return employeeRepository.findByUser_Id(userId).orElse(null);
    }
    @Override //adressId listeleme
    public Address getOneByAddressId(Long addressId) { //spesifik bir kullanıcı listelenecek
        return employeeRepository.findById(addressId).orElse(null);
    }

    @Override
    public Address createProfile(CreateProfileRequest createProfileRequest) {
        User user = userService.getOneUserById(createProfileRequest.getId()); //user kontrolü yapıp post ekleyeceğiz
        if (user == null)
            return null;

        Address toSave = new Address();
        //toSave.setAddressId(createProfileRequest.getId());
        toSave.setStreet(createProfileRequest.getStreet());
        toSave.setDistrict(createProfileRequest.getDistrict());
        toSave.setCity(createProfileRequest.getCity());
        toSave.setCountry(createProfileRequest.getCountry());
        toSave.setApartmentNumber(createProfileRequest.getApartmentNumber());
        toSave.setDoorNumber(createProfileRequest.getDoorNumber());
        toSave.setUser(user);
        return employeeRepository.save(toSave);
    }

    //adres güncelleme
    @Override
    public Address updateAddress(CreateProfileRequest createProfileRequest) {
        //Address address = employeeRepository.findById(createProfileRequest.getId());
        return employeeRepository.findById(createProfileRequest.getId())
                .map(address -> {
                    address.setCountry(createProfileRequest.getCountry());
                    address.setCity(createProfileRequest.getCity());
                    address.setDistrict(createProfileRequest.getDistrict());
                    address.setStreet(createProfileRequest.getStreet());
                    address.setApartmentNumber(createProfileRequest.getApartmentNumber());
                    address.setDoorNumber(createProfileRequest.getDoorNumber());
                    return employeeRepository.save(address);
                })
                .orElseGet(() -> {
                    return null;
                });
/*
        if(address == null)
        return null;

        Address toSave = new Address();
        toSave.setStreet(createProfileRequest.getStreet());
        toSave.setDistrict(createProfileRequest.getDistrict());
        toSave.setCity(createProfileRequest.getCity());
        toSave.setCountry(createProfileRequest.getCountry());
        toSave.setApartmentNumber(createProfileRequest.getApartmentNumber());
        toSave.setDoorNumber(createProfileRequest.getDoorNumber());
        return employeeRepository.save(toSave);
*/
    }

}
