package com.employeemanagementsystem.projectx.dto;

import lombok.Data;

@Data
public class RegistrationDto {

    private Long id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
}
