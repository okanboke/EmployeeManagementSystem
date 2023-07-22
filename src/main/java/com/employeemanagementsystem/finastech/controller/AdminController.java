package com.employeemanagementsystem.finastech.controller;

import com.employeemanagementsystem.finastech.entity.User;
import com.employeemanagementsystem.finastech.exception.UserNotFoundException;
import com.employeemanagementsystem.finastech.response.UserResponse;
import com.employeemanagementsystem.finastech.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImpl userService;

    @Autowired
    public AdminController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/list-user")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }   //userService sınıfındaki metoda gider User listeler.

    @GetMapping("/list-user/{userId}")
    public UserResponse getOneUser(@PathVariable Long userId) { //tek bir user çekilmek istendiğinde Service sınıfına gider ve metoduyla gerçekleştirir.
        User user = userService.getOneUserById(userId);

        if(user == null) { //öyle bir user yoksa
            throw new UserNotFoundException();
        }
        return new UserResponse(user);
    }

    @PostMapping("/create-user") //User ekleme
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void handleUserNotFound() { //user bulunamaz ise buraya gelecek
        //message
    }
}
