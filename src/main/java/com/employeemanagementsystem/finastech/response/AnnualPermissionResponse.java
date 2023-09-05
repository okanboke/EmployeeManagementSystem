package com.employeemanagementsystem.finastech.response;

import com.employeemanagementsystem.finastech.entity.AnnualPermission;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class AnnualPermissionResponse {

    private Long permissionId;
    private Long id;
    private String type;
    private String userName;
    private String firstName;
    private String lastName;
    private String contactPersonName;
    private String contactPerson;
    private String travelLocation;
    private Date startDate;
    private Date endDate;
    private Boolean approvalStatus;

    public AnnualPermissionResponse(AnnualPermission entity) {
        this.permissionId = entity.getPermissionId();
        this.id = entity.getUser().getId();
        this.userName = entity.getUser().getUserName();
        this.firstName = entity.getUser().getFirstName();
        this.lastName = entity.getUser().getLastName();
        this.type = entity.getType();
        this.contactPersonName = entity.getContactPersonName();
        this.contactPerson = entity.getContactPerson();
        this.travelLocation = entity.getTravelLocation();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.approvalStatus = entity.getApprovalStatus();

    }
}
