package com.employeemanagementsystem.finastech.response;

import com.employeemanagementsystem.finastech.entity.Role;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String message;
    private Long userId;
    private String accessToken;
    private String refreshToken;
    private List<Role> roles;
}
