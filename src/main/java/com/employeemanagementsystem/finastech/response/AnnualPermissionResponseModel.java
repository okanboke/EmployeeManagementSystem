package com.employeemanagementsystem.finastech.response;

import com.employeemanagementsystem.finastech.entity.AnnualPermission;
import com.employeemanagementsystem.finastech.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AnnualPermissionResponseModel {

    private Long permissionId;
    private User user;
    private String type;
    private Date startDate;
    private Date endDate;
    private String contactPersonName;
    private String contactPerson;
    private String travelLocation;
    private Boolean approvalStatus;

    private String errorMessage;
    private int errorCode;

    public AnnualPermissionResponseModel(AnnualPermission entity) {
        this.permissionId = entity.getPermissionId();
        this.user = entity.getUser();
        this.type = entity.getType();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.contactPersonName = entity.getContactPersonName();
        this.contactPerson = entity.getContactPerson();
        this.travelLocation = entity.getTravelLocation();
        this.approvalStatus = entity.isApprovalStatus();
    }

}
