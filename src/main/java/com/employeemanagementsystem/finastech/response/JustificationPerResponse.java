package com.employeemanagementsystem.finastech.response;

import com.employeemanagementsystem.finastech.entity.JustificationPermission;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class JustificationPerResponse {

    //Get Justification Permissions Admin/User panel
    private Long id;
    private Long userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String permissionType;
    private String permissionDescription;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean approvalStatus;

    public JustificationPerResponse(JustificationPermission entity) {
        this.id = entity.getPermissionId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getUserName();
        this.firstName = entity.getUser().getFirstName();
        this.lastName = entity.getUser().getLastName();
        this.permissionType = entity.getJustPerType().getJustPermissionType();
        this.permissionDescription = entity.getPermissionDescription();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.approvalStatus = entity.getApprovalStatus();
    }


}
