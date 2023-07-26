package com.employeemanagementsystem.finastech.controller;

import com.employeemanagementsystem.finastech.entity.Role;
import com.employeemanagementsystem.finastech.entity.User;
import com.employeemanagementsystem.finastech.repository.RoleRepository;
import com.employeemanagementsystem.finastech.request.UserRequest;
import com.employeemanagementsystem.finastech.security.JwtTokenProvider;
import com.employeemanagementsystem.finastech.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private UserServiceImpl userService;

    private PasswordEncoder passwordEncoder;

    private RoleRepository roleRepository;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenProvider jwtTokenProvider,
                          UserServiceImpl userService,
                          PasswordEncoder passwordEncoder,
                          RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/login") //Giriş
    public String login(@RequestBody UserRequest loginRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        return "Bearer " +  jwtToken;

    }

    @PostMapping("/admin/register") //Kayıt
    public ResponseEntity<String> register(@RequestBody UserRequest registerRequest) {
        if(userService.getOneUserByUserName(registerRequest.getUserName()) != null)
            return new ResponseEntity<>("Kullanıcı adı zaten kayıtlı", HttpStatus.BAD_REQUEST);


        User user = new User();
        user.setUserName(registerRequest.getUserName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        Role role = roleRepository.findByRoleName("user");
        user.setRoles(Arrays.asList(role));
        userService.createUser(user);
        return new ResponseEntity<>("Kullanıcı kaydı başarılı", HttpStatus.CREATED);

    }

}
