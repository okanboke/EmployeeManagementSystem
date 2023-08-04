package com.employeemanagementsystem.finastech.repository;

import com.employeemanagementsystem.finastech.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Address, Long> {

}
