package com.employeemanagementsystem.projectx.repository;

import com.employeemanagementsystem.projectx.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName); //giriş için email sorgulayacak.


    @Query("select u from User u  where u.roles in :roles")
    public List<User> findByRoles(@Param("roles") List<String> roles);
}
