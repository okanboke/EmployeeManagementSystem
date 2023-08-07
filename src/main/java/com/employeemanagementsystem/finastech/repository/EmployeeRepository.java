package com.employeemanagementsystem.finastech.repository;

import com.employeemanagementsystem.finastech.entity.Address;
import com.employeemanagementsystem.finastech.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Address, Long> {
    //@Query( "select a from Address a  where a.user.id in :userId" )
    @Query("select a from Address a where a.user.id in  :userId")
    Optional<Address> findByUser_Id(Long userId);
}
