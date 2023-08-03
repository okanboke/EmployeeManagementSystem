package com.employeemanagementsystem.finastech.response;

import com.employeemanagementsystem.finastech.entity.Role;
import com.employeemanagementsystem.finastech.entity.User;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String message;
    private Long userId;
    private String accessToken;
    private String refreshToken;
    private List<Role> roles;



}
