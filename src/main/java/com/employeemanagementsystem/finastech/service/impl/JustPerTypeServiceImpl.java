package com.employeemanagementsystem.finastech.service.impl;

import com.employeemanagementsystem.finastech.entity.JustPerType;
import com.employeemanagementsystem.finastech.entity.JustificationPermission;
import com.employeemanagementsystem.finastech.repository.JustPerTypeRepository;
import com.employeemanagementsystem.finastech.response.JustPerTypeResponse;
import com.employeemanagementsystem.finastech.response.JustificationPerResponse;
import com.employeemanagementsystem.finastech.service.JustPerTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JustPerTypeServiceImpl implements JustPerTypeService {

    private final JustPerTypeRepository justPerTypeRepository;

    public JustPerTypeServiceImpl(JustPerTypeRepository justPerTypeRepository) {
        this.justPerTypeRepository = justPerTypeRepository;


    }

    @Override
    public List<JustPerTypeResponse> getAllJustPerType() {
        List<JustPerType> list;
        list = justPerTypeRepository.findAll();
        return null;
    }
}
