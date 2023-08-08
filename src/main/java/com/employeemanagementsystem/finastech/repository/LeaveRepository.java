package com.employeemanagementsystem.finastech.repository;

import com.employeemanagementsystem.finastech.entity.Leaves;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRepository extends JpaRepository<Leaves, Long> {


}
