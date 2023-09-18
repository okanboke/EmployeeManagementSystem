package com.employeemanagementsystem.projectx.controller;

import com.employeemanagementsystem.projectx.entity.RefreshToken;
import com.employeemanagementsystem.projectx.entity.Role;
import com.employeemanagementsystem.projectx.entity.User;
import com.employeemanagementsystem.projectx.repository.RoleRepository;
import com.employeemanagementsystem.projectx.repository.UserRepository;
import com.employeemanagementsystem.projectx.request.RefreshRequest;
import com.employeemanagementsystem.projectx.request.UserRequest;
import com.employeemanagementsystem.projectx.response.AuthResponse;
import com.employeemanagementsystem.projectx.security.JwtTokenProvider;
import com.employeemanagementsystem.projectx.service.impl.RefreshTokenService;
import com.employeemanagementsystem.projectx.service.impl.UserServiceImpl;
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

    //admin Login
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
        return authResponse;
        //return "Bearer " +  jwtToken;
    }

    //user Login
    @PostMapping("/user/login") //Giriş
    public ResponseEntity<AuthResponse> userLogin(@RequestBody UserRequest loginRequest) {
        AuthResponse authResponse = new AuthResponse();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken
                (loginRequest.getUserName(), loginRequest.getPassword());
        try {
            Authentication auth = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(auth);
            String jwtToken = jwtTokenProvider.generateJwtToken(auth);
            User user = userService.getOneUserByUserName(loginRequest.getUserName());
            authResponse.setAccessToken("Bearer " + jwtToken);
            authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
            authResponse.setUserId(user.getId());
            authResponse.setRoles(user.getRoles());
            authResponse.setMessage("Kullanıcı Girişi Başarılı");
            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {
            authResponse.setMessage("Email veya şifre hatalı!");
            return new ResponseEntity<>(authResponse, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/admin/create-user") //Kayıt
    public ResponseEntity<String> register(@RequestBody UserRequest registerRequest) {
        if (userService.getOneUserByUserName(registerRequest.getUserName()) != null)
            return new ResponseEntity<>("Kullanıcı adı zaten kayıtlı", HttpStatus.NOT_ACCEPTABLE);
        User user = new User();
        user.setUserName(registerRequest.getUserName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setUserDate(registerRequest.getUserDate());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setRestDay(registerRequest.getRestDay());
        user.setAnnualUpdateDate(registerRequest.getUserDate()); //user'ın sisteme kaydolduğu gün null olmaması için izin güncelleme tarihi de işe giriş tarihi ile aynı olmalıdır.
        Role role = roleRepository.findByRoleName("user");
        user.setRoles(Arrays.asList(role));
        userService.createUser(user);
        return new ResponseEntity<>("Kullanıcı kaydı başarılı", HttpStatus.CREATED);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshRequest refreshRequest) {
        AuthResponse response = new AuthResponse();
        RefreshToken token = refreshTokenService.getByUser(refreshRequest.getUserId());
        if (token.getToken().equals(refreshRequest.getRefreshToken()) &&
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
