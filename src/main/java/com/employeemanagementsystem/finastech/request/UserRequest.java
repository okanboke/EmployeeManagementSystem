package com.employeemanagementsystem.finastech.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserRequest {

    private Long id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
}