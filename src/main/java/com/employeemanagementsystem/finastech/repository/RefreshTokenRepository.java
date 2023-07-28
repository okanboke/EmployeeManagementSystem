package com.employeemanagementsystem.finastech.repository;

import com.employeemanagementsystem.finastech.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    RefreshToken findByUserId(Long userId);
}
