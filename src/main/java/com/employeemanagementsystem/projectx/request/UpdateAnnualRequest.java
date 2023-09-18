package com.employeemanagementsystem.projectx.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAnnualRequest {

    //admin izin onayı
    private Long permissionId;
    private boolean approvalStatus;

}
