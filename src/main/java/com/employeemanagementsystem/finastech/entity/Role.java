package com.employeemanagementsystem.finastech.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;
    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
    @JsonIgnore //sonsuz döngüye girmemesi için(Önemli)
    private List<User> users = new ArrayList<>();
}
