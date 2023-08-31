package com.employeemanagementsystem.finastech.repository;

import com.employeemanagementsystem.finastech.entity.AnnualPermission;
import com.employeemanagementsystem.finastech.response.AnnualPermissionResponse;
import com.employeemanagementsystem.finastech.response.JustificationPerResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnualPermissionRepository extends JpaRepository<AnnualPermission, Long> {

    @Query("select a from AnnualPermission a where a.user.id in :userId")
    List<AnnualPermissionResponse> findByUser_Id(Long userId);

}
