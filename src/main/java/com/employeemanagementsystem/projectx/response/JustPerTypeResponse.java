package com.employeemanagementsystem.projectx.response;

import com.employeemanagementsystem.projectx.entity.JustPerType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JustPerTypeResponse {

    private Long justPerId;
    private String justPermissionType;

    public JustPerTypeResponse(JustPerType entity) {
        this.justPerId = entity.getJustPerId();
        this.justPermissionType = entity.getJustPermissionType();
    }

}
