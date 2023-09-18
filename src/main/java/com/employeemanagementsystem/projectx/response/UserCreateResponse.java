package com.employeemanagementsystem.projectx.response;

import com.employeemanagementsystem.projectx.entity.Address;
import com.employeemanagementsystem.projectx.entity.User;
import lombok.Data;

@Data
public class UserCreateResponse {


    private Long id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;

    //address tablo
    private Long addressId; //
    private String street;
    private String district;
    private String city;
    private String country;
    private int apartmentNumber;
    private int doorNumber;

    public UserCreateResponse(User entity1, Address entity2) {
        this.id = entity1.getId();
        this.userName = entity1.getUserName();
        this.password = entity1.getPassword();
        this.firstName = entity1.getFirstName();
        this.lastName = entity1.getLastName();
        this.addressId = entity2.getAddressId();
        this.street = entity2.getStreet();
        this.district = entity2.getDistrict();
        this.city = entity2.getCity();
        this.country = entity2.getCountry();
        this.apartmentNumber = entity2.getApartmentNumber();
        this.doorNumber = entity2.getDoorNumber();
    }
}
