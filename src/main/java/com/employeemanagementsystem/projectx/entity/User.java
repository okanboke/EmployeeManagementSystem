package com.employeemanagementsystem.projectx.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Id üretecek
    private Long id;

    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private int restDay;
    private String phoneNumber;
    //private boolean isActive = true;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date annualUpdateDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date userDate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<Role> roles = new ArrayList<>();//çoka çok ilişkili

}
