package com.employeemanagementsystem.projectx.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "justification_permission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JustificationPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Id üretecek
    private Long permissionId;

    @ManyToOne(fetch = FetchType.EAGER) //bire çok ilişki uyguluyoruz bir çok ürünün tek bir user'ı olabilir.
    @JoinColumn(name = "user_id", nullable = false) //JoinColumn ile tabloları birbirine bağlıyoruz.
    @OnDelete(action = OnDeleteAction.CASCADE) //Bir user silindiğinde onun bütün ürünleri silinmeli.
    @JsonIgnore
    private User user;

    //private String permissionType;
    private String permissionDescription;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean approvalStatus = false; //izin istendiğinde false olacak sonra admin onaylayacak.

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "just_per_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private JustPerType justPerType;

    @Transient
    private String errorMessage;
}
