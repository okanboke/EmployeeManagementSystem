package com.employeemanagementsystem.finastech.response;

import com.employeemanagementsystem.finastech.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {

    private Long userId;
    private Long addressId;
    private String street;
    private String district;
    private String city;
    private String country;
    private int apartmentNumber;
    private int doorNumber;


    public AddressResponse(Address entity) {
        this.userId = entity.getUser().getId();
        this.addressId = entity.getAddressId();
        this.street = entity.getStreet();
        this.district = entity.getDistrict();
        this.city = entity.getCity();
        this.country = entity.getCountry();
        this.apartmentNumber = entity.getApartmentNumber();
        this.doorNumber = entity.getDoorNumber();
    }
}
