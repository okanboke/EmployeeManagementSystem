package com.employeemanagementsystem.finastech.repository;

import com.employeemanagementsystem.finastech.entity.JustPerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JustPerTypeRepository extends JpaRepository<JustPerType, Long> {


}
