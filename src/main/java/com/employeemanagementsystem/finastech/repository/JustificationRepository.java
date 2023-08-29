package com.employeemanagementsystem.finastech.repository;

import com.employeemanagementsystem.finastech.entity.JustificationPermission;
import com.employeemanagementsystem.finastech.response.JustificationPerResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JustificationRepository extends JpaRepository<JustificationPermission, Long> {

    @Query("select a from JustificationPermission a where a.user.id in :userId")
    List<JustificationPerResponse> findByUser_Id(Long userId);


}
