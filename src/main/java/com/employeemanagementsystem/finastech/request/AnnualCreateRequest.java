package com.employeemanagementsystem.finastech.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnnualCreateRequest {

    private Long permissionId;
    private Long userId;
    private String type = "Yıllık İzin";
    private String contactPersonName;
    private String contactPerson;
    private String travelLocation;
    private Date startDate;
    private Date endDate;
    private Boolean approvalStatus = false;
}
