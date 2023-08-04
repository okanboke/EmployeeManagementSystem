package com.employeemanagementsystem.finastech.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProfileResponse {

    private Long id;
    private String street;
    private String district;
    private String city;
    private String country;
    private int apartmentNumber;
    private int doorNumber;
}
