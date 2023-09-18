package com.employeemanagementsystem.projectx.response;

import com.employeemanagementsystem.projectx.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class AllUserResponse {

    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date userDate;
    private int usersCount;

    public AllUserResponse(User entity) {
        this.id = entity.getId();
        this.userName = entity.getUserName();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.userDate = entity.getUserDate();
        this.usersCount = entity.getRoles().size(); //Toplam kullanıcı sayısını verir
    }
}
