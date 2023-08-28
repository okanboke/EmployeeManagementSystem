package com.employeemanagementsystem.finastech.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

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
    @JoinColumn(name="user_id", nullable = false) //JoinColumn ile tabloları birbirine bağlıyoruz.
    @OnDelete(action = OnDeleteAction.CASCADE) //Bir user silindiğinde onun bütün ürünleri silinmeli.
    @JsonIgnore
    private User user;

    private String type = "Yıllık İzin";
    private LocalDate startDate;
    private LocalDate endDate;
    private String contactPersonName;
    private int contactPerson;
    private String travelLocation;
    private Boolean approvalStatus; //izin istendiğinde false olacak sonra admin onaylayacak.

}