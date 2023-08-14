package com.employeemanagementsystem.finastech.service;

import com.employeemanagementsystem.finastech.entity.JustPerType;
import com.employeemanagementsystem.finastech.response.JustPerTypeResponse;

import java.util.List;

public interface JustPerTypeService {
    List<JustPerTypeResponse> getAllJustPerType();

    JustPerType createJustPerType(JustPerType justPerType);

    JustPerType getOneJustPerTypeById(Long permissionTypeId);
}
