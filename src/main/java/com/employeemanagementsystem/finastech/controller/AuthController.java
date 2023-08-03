package com.employeemanagementsystem.finastech.controller;

import com.employeemanagementsystem.finastech.entity.RefreshToken;
import com.employeemanagementsystem.finastech.entity.Role;
import com.employeemanagementsystem.finastech.entity.User;
import com.employeemanagementsystem.finastech.repository.RoleRepository;
import com.employeemanagementsystem.finastech.repository.UserRepository;
import com.employeemanagementsystem.finastech.request.RefreshRequest;
import com.employeemanagementsystem.finastech.request.UserRequest;
import com.employeemanagementsystem.finastech.response.AuthResponse;
import com.employeemanagementsystem.finastech.security.JwtTokenProvider;
import com.employeemanagementsystem.finastech.service.impl.RefreshTokenService;
import com.employeemanagementsystem.finastech.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private UserServiceImpl userService;

    private PasswordEncoder passwordEncoder;

    private RoleRepository roleRepository;

    private RefreshTokenService refreshTokenService;

    private UserRepository userRepository;


    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenProvider jwtTokenProvider,
                          UserServiceImpl userService,
                          PasswordEncoder passwordEncoder,
                          RoleRepository roleRepository,
                          RefreshTokenService refreshTokenService,
                          UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.refreshTokenService = refreshTokenService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login") //Giriş
    public AuthResponse login(@RequestBody UserRequest loginRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        User user = userService.getOneUserByUserName(loginRequest.getUserName());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccessToken("Bearer " + jwtToken);
        authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
        authResponse.setUserId(user.getId());
        authResponse.setRoles(user.getRoles());
        //.stream().collect(Collectors.toUnmodifiableList()); //role bilgisi
        return authResponse;
        //return "Bearer " +  jwtToken;

    }
    @PostMapping("/admin/create-user") //Kayıt
    public ResponseEntity<String> register(@RequestBody UserRequest registerRequest) {
        if(userService.getOneUserByUserName(registerRequest.getUserName()) != null)
            return new ResponseEntity<>("Kullanıcı adı zaten kayıtlı", HttpStatus.BAD_REQUEST);
        User user = new User();
        user.setUserName(registerRequest.getUserName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setUserDate(registerRequest.getUserDate());
        Role role = roleRepository.findByRoleName("user");
        user.setRoles(Arrays.asList(role));
        userService.createUser(user);
        return new ResponseEntity<>("Kullanıcı kaydı başarılı", HttpStatus.CREATED);
    }
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshRequest refreshRequest) {
        AuthResponse response = new AuthResponse();
        RefreshToken token = refreshTokenService.getByUser(refreshRequest.getUserId());
        if(token.getToken().equals(refreshRequest.getRefreshToken()) &&
                !refreshTokenService.isRefreshExpired(token)) {

            User user = token.getUser();
            String jwtToken = jwtTokenProvider.generateJwtTokenByUserId(user.getId());
            response.setMessage("token successfully refreshed.");
            response.setAccessToken("Bearer " + jwtToken);
            response.setUserId(user.getId());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setMessage("refresh token is not valid.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

    }


}
