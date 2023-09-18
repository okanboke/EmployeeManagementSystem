package com.employeemanagementsystem.projectx.repository;

import com.employeemanagementsystem.projectx.entity.JustPerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JustPerTypeRepository extends JpaRepository<JustPerType, Long> {


}
