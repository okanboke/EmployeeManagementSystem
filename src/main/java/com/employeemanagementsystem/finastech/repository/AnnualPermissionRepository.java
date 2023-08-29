package com.employeemanagementsystem.finastech.repository;

import com.employeemanagementsystem.finastech.entity.AnnualPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnualPermissionRepository extends JpaRepository<AnnualPermission, Long> {

}
