package com.employeemanagementsystem.finastech.response;

import com.employeemanagementsystem.finastech.entity.Role;
import com.employeemanagementsystem.finastech.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    //private List<Role> roles;


    public AllUserResponse(User entity) {
        this.id = entity.getId();
        this.userName = entity.getUserName();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.userDate = entity.getUserDate();
        this.usersCount = entity.getRoles().size(); //Toplam kullanıcı sayısını verir
        //this.roles = entity.getRoles().stream().collect(Collectors.toUnmodifiableList());//Bağlı olduğu tablodan rolleri çekiyoruz
    }

}
