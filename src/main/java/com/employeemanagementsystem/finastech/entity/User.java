package com.employeemanagementsystem.finastech.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Id üretecek
    private Long id;

    private String userName;
    private String password;
    private String firstName;
    private String lastName;

}
