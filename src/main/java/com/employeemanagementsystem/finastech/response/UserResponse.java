package com.employeemanagementsystem.finastech.response;

import com.employeemanagementsystem.finastech.entity.User;
import lombok.Data;

@Data
public class UserResponse {

    private Long id;
    private String userName;
    private String firstName;
    private String lastName;

    public UserResponse(User entity) {
        this.id = entity.getId();
        this.userName = entity.getUserName();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
    }
}
