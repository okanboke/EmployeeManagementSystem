package com.employeemanagementsystem.finastech.repository;

import com.employeemanagementsystem.finastech.entity.JustificationPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JustificationRepository extends JpaRepository<JustificationPermission, Long> {


}
