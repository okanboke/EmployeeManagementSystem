package com.employeemanagementsystem.finastech.repository;

import com.employeemanagementsystem.finastech.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName); //giriş için email sorgulayacak.


    @Query( "select u from User u  where u.roles in :roles" )
    public List<User> findByRoles(@Param("roles") List<String> roles);
}
