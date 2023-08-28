package com.employeemanagementsystem.finastech.request;

import com.employeemanagementsystem.finastech.entity.Role;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {

    private Long id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int restDay;
    private Date userDate;
}
