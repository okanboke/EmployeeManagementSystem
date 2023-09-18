package com.employeemanagementsystem.projectx.repository;

import com.employeemanagementsystem.projectx.entity.AnnualPermission;
import com.employeemanagementsystem.projectx.response.AnnualPermissionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnualPermissionRepository extends JpaRepository<AnnualPermission, Long> {

    @Query("select a from AnnualPermission a where a.user.id in :userId")
    List<AnnualPermissionResponse> findByUser_Id(Long userId);

}
