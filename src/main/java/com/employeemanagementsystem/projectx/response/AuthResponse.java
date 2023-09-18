package com.employeemanagementsystem.projectx.response;

import com.employeemanagementsystem.projectx.entity.Role;
import lombok.*;

import java.util.List;

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
