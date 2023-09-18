package com.employeemanagementsystem.projectx.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {

    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private int restDay;
    private String phoneNumber;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date annualUpdateDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date userDate;
    private String roles;
}
