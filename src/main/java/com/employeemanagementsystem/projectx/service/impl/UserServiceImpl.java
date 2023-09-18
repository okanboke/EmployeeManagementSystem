package com.employeemanagementsystem.projectx.service.impl;

import com.employeemanagementsystem.projectx.dto.RegistrationDto;
import com.employeemanagementsystem.projectx.entity.Role;
import com.employeemanagementsystem.projectx.entity.User;
import com.employeemanagementsystem.projectx.repository.RoleRepository;
import com.employeemanagementsystem.projectx.repository.UserRepository;
import com.employeemanagementsystem.projectx.request.UserRequest;
import com.employeemanagementsystem.projectx.response.AllUserResponse;
import com.employeemanagementsystem.projectx.response.UserInfoResponse;
import com.employeemanagementsystem.projectx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User createUser(User newUser) { //User kaydı oluşturur
        return userRepository.save(newUser);
    }

    @Override
    public List<AllUserResponse> getAllUsers() { //tüm kullanıcılar listelenecek
        List<User> list;
        list = userRepository.findAll();

        return list.stream().map(users -> new AllUserResponse(users)).collect(Collectors.toList());
    }

    @Override
    public User getOneUserById(Long userId) { //spesifik bir kullanıcı listelenecek
        return userRepository.findById(userId).orElse(null);
    }

    public User getOneUserByUserName(String userName) { //database'e gidip böyle bir user var mı yok mu onu kontrol edeceğiz.
        return userRepository.findByUserName(userName);
    }

    //Teddy
    @Override
    public void saveUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setUserName(registrationDto.getUserName());
        user.setPassword(registrationDto.getPassword());
        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
        Role role = roleRepository.findByRoleName("user");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override //Post işlemi ile spesifik user bilgileri eşitlenip return ediliyor.
    public UserInfoResponse getOneUserInfoById(UserRequest userInfoRequest) {
        Optional<User> user = userRepository.findById(userInfoRequest.getId()); //user kontrolü yapıp post ekleyeceğiz
        if (user == null)
            return null;

        UserInfoResponse toGet = new UserInfoResponse();
        toGet.setId(user.get().getId());
        toGet.setUserName(user.get().getUserName());
        toGet.setFirstName(user.get().getFirstName());
        toGet.setLastName(user.get().getLastName());
        toGet.setRestDay(user.get().getRestDay());
        toGet.setPhoneNumber(user.get().getPhoneNumber());
        toGet.setAnnualUpdateDate(user.get().getAnnualUpdateDate());
        toGet.setUserDate(user.get().getUserDate());
        toGet.setRoles(user.get().getRoles().listIterator().next().getRoleName()); //List Iterator ile roleName'e ulaşıyoruz.
        return toGet;
    }
    //Çalışan profil ekleme


}
