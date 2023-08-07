package com.employeemanagementsystem.finastech.response;

import com.employeemanagementsystem.finastech.entity.Address;
import com.employeemanagementsystem.finastech.entity.User;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private Date userDate;



    public UserResponse(User entity) {
        this.id = entity.getId();
        this.userName = entity.getUserName();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.userDate = entity.getUserDate();



    }
}
