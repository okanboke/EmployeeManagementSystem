package com.employeemanagementsystem.finastech.controller;
import com.employeemanagementsystem.finastech.entity.User;
import com.employeemanagementsystem.finastech.exception.UserNotFoundException;
import com.employeemanagementsystem.finastech.response.AllUserResponse;
import com.employeemanagementsystem.finastech.response.JustificationPerResponse;
import com.employeemanagementsystem.finastech.response.UserResponse;
import com.employeemanagementsystem.finastech.service.impl.JustificationServiceImpl;
import com.employeemanagementsystem.finastech.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserServiceImpl userService;

    private final JustificationServiceImpl justificationService;

    @Autowired
    public AdminController(UserServiceImpl userService,
                           JustificationServiceImpl justificationService) {
        this.userService = userService;
        this.justificationService = justificationService;
    }

    //Tüm kullanıcıları listeleme
    @GetMapping("/list-user")
    public List<AllUserResponse> getAllUsers(){
        return userService.getAllUsers();
    }   //userService sınıfındaki metoda gider User listeler.

    //spesifik bir kullanıcı listeleme
    @GetMapping("/list-user/{userId}")
    public UserResponse getOneUser(@PathVariable Long userId) { //tek bir user çekilmek istendiğinde Service sınıfına gider ve metoduyla gerçekleştirir.
        User user = userService.getOneUserById(userId);

        if(user == null) { //öyle bir user yoksa
            throw new UserNotFoundException();
        }
        return new UserResponse(user);
    }

    //Justification Permission listeleme
    @GetMapping("/list-justification") //Role Admin
    public List<JustificationPerResponse> getAllJustificationPermission(){
        return justificationService.getAllJustificationPermission();
    }

    //kullanıcı ekleme
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

    @GetMapping({"/forAdmin"})
    public String forAdmin() {
        return "this URL is only accessible from admin";
    }

    @GetMapping({"/forUser"})
    public String forUser() {
        return "this URL is only accessible from user";
    }
}
