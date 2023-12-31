package com.employeemanagementsystem.projectx.service.impl;

import com.employeemanagementsystem.projectx.entity.JustPerType;
import com.employeemanagementsystem.projectx.repository.JustPerTypeRepository;
import com.employeemanagementsystem.projectx.response.JustPerTypeResponse;
import com.employeemanagementsystem.projectx.service.JustPerTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JustPerTypeServiceImpl implements JustPerTypeService {

    private final JustPerTypeRepository justPerTypeRepository;

    public JustPerTypeServiceImpl(JustPerTypeRepository justPerTypeRepository) {
        this.justPerTypeRepository = justPerTypeRepository;


    }

    //for User Get
    @Override
    public List<JustPerTypeResponse> getAllJustPerType() {
        List<JustPerType> list;
        list = justPerTypeRepository.findAll();
        return list.stream().map(justification_permission_type -> new JustPerTypeResponse(justification_permission_type)).collect(Collectors.toList());

    }

    //for Admin Post
    @Override
    public JustPerType createJustPerType(JustPerType justPerType) {
        return justPerTypeRepository.save(justPerType);
    }

    //spesifik bir tür arayacak
    @Override
    public JustPerType getOneJustPerTypeById(Long permissionTypeId) {
        return justPerTypeRepository.findById(permissionTypeId).orElse(null);

    }

    //for admin izin türü silme
    @Override
    public void deleteJustPerType(Long justPerId) {
        justPerTypeRepository.deleteById(justPerId);
    }

}
