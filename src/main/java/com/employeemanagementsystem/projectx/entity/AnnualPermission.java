package com.employeemanagementsystem.projectx.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "annual_permissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnnualPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Id üretecek
    private Long permissionId;

    @ManyToOne(fetch = FetchType.EAGER) //bire çok ilişki uyguluyoruz bir çok ürünün tek bir user'ı olabilir.
    @JoinColumn(name = "user_id", nullable = false) //JoinColumn ile tabloları birbirine bağlıyoruz.
    @OnDelete(action = OnDeleteAction.CASCADE) //Bir user silindiğinde onun bütün ürünleri silinmeli.
    @JsonIgnore
    private User user;

    private String type;
    private Date startDate;
    private Date endDate;
    private String contactPersonName;
    private String contactPerson;
    private String travelLocation;
    private boolean approvalStatus = false; //izin istendiğinde false olacak sonra admin onaylayacak.

    @Transient
    private String errorMessage;
}
