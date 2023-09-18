package com.employeemanagementsystem.projectx.repository;

import com.employeemanagementsystem.projectx.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Address, Long> {
    //@Query( "select a from Address a  where a.user.id in :userId" )
    @Query("select a from Address a where a.user.id in  :userId")
    Optional<Address> findByUser_Id(Long userId);
}
