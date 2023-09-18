package com.employeemanagementsystem.projectx.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "justification_permission_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JustPerType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Id üretecek
    private Long justPerId;

    private String justPermissionType;
}
