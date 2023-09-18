package com.employeemanagementsystem.projectx.controller;

import com.employeemanagementsystem.projectx.entity.Address;
import com.employeemanagementsystem.projectx.entity.User;
import com.employeemanagementsystem.projectx.exception.UserNotFoundException;
import com.employeemanagementsystem.projectx.request.CreateProfileRequest;
import com.employeemanagementsystem.projectx.request.UserRequest;
import com.employeemanagementsystem.projectx.response.AddressResponse;
import com.employeemanagementsystem.projectx.response.UserInfoResponse;
import com.employeemanagementsystem.projectx.response.UserResponse;
import com.employeemanagementsystem.projectx.service.impl.EmployeeServiceImpl;
import com.employeemanagementsystem.projectx.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    private final UserServiceImpl userService;

    public EmployeeController(EmployeeServiceImpl employeeService,
                              UserServiceImpl userService) { //Constructor Injection
        this.employeeService = employeeService;
        this.userService = userService;
    }

    //profile user info
    @GetMapping("/profile/{userId}")
    public UserResponse getOneUser(@PathVariable Long userId) { //tek bir user çekilmek istendiğinde Service sınıfına gider ve metoduyla gerçekleştirir.
        User user = userService.getOneUserById(userId);

        if (user == null) { //öyle bir user yoksa
            throw new UserNotFoundException();
        }
        return new UserResponse(user);
    }

    //spesifik user bilgileri.
    @PostMapping("/user-home/userInfo")
    public ResponseEntity<UserInfoResponse> getOneUserInfo(@RequestBody UserRequest userInfoRequest) {
        //return justificationService.getOneUserPermissions(userId);
        UserInfoResponse list = new UserInfoResponse();
        list = userService.getOneUserInfoById(userInfoRequest);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //profile user address info
    @GetMapping("/profile/address/{userId}")
    public AddressResponse getOneAddress(@PathVariable Long userId) {
        Address address = employeeService.getOneByAddressUserId(userId);

        if (address == null) { //öyle bir adress yok
            throw new UserNotFoundException();
        }
        return new AddressResponse(address);
    }

    //adress güncelleme
    @GetMapping("/profile/address/currentuser/{addressId}")
    public AddressResponse updateGetAddress(@PathVariable Long addressId) {
        Address address = employeeService.getOneByAddressId(addressId);

        /*if(address == null) { //öyle bir adress yok
            throw new UserNotFoundException();
        }*/
        return new AddressResponse(address);
    }

    //Çalışan adres ve profil bilgileri ekleme
    @PostMapping("/edit-profile")
    public Address createProfile(
            @RequestBody CreateProfileRequest createProfileRequest) {
        return employeeService.createProfile(createProfileRequest);
    }

    //çalışan adres güncelleme
    @PutMapping("/update-address")
    public Address updateAddress(
            @RequestBody CreateProfileRequest createProfileRequest) {
        return employeeService.updateAddress(createProfileRequest);
    }
}
