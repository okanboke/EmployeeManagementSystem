package com.employeemanagementsystem.projectx.service;

import com.employeemanagementsystem.projectx.entity.JustPerType;
import com.employeemanagementsystem.projectx.response.JustPerTypeResponse;

import java.util.List;

public interface JustPerTypeService {
    List<JustPerTypeResponse> getAllJustPerType();

    JustPerType createJustPerType(JustPerType justPerType);

    JustPerType getOneJustPerTypeById(Long permissionTypeId);

    void deleteJustPerType(Long justPerId);
}
