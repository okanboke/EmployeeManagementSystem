package com.employeemanagementsystem.finastech.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JustificationCreateRequest {

    private Long permissionId;
    private Long userId;
    private String permissionType;
    private String permissionDescription;
    private LocalDate startDate;
    private LocalDate endDate;
    private String approvalStatus;

}
