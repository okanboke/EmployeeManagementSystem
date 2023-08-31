package com.employeemanagementsystem.finastech.service.impl;

import com.employeemanagementsystem.finastech.entity.AnnualPermission;
import com.employeemanagementsystem.finastech.entity.User;
import com.employeemanagementsystem.finastech.repository.AnnualPermissionRepository;
import com.employeemanagementsystem.finastech.repository.UserRepository;
import com.employeemanagementsystem.finastech.request.AnnualCreateRequest;
import com.employeemanagementsystem.finastech.response.AnnualPermissionResponse;
import com.employeemanagementsystem.finastech.service.AnnualPermissionService;
import com.employeemanagementsystem.finastech.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Calendar;
import java.util.stream.Collectors;


@Service
public class AnnualPermissionServiceImpl implements AnnualPermissionService {

    private final AnnualPermissionRepository annualPermissionRepository;

    private final UserRepository userRepository;

    private final UserService userService;

    public AnnualPermissionServiceImpl(AnnualPermissionRepository annualPermissionRepository,
                                       UserRepository userRepository,
                                       UserService userService) {
        this.annualPermissionRepository = annualPermissionRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }
    @Override //scheduled component'inde gece yarısı çalışacak olan izin günü tanımlama görevinin fonksiyonu
    public void updateRestDaysForUsers() {
        List<User> users = userRepository.findAll();

        for (User user : users) {
            Date startDate = user.getUserDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(Calendar.YEAR, 1);
            //izin eklenme tarihi
            Date updateDateTime = user.getAnnualUpdateDate();
            Date now = new Date();

            Calendar updateCalendar = Calendar.getInstance();
            if(updateDateTime != null) {
                updateCalendar.setTime(updateDateTime);
            }

            updateCalendar.add(Calendar.YEAR, 1);
            Date oneYearLater = updateCalendar.getTime();

            if (calendar.getTime().compareTo(new Date()) <= 0 && !now.before(oneYearLater)) {

                    int currentRestDays = user.getRestDay();
                    user.setRestDay(currentRestDays + 15);
                    user.setAnnualUpdateDate(Calendar.getInstance().getTime());
                    userRepository.save(user);
            }
        }
    }

    @Override
    public AnnualPermission createPermission(AnnualCreateRequest annualCreateRequest) {
        User user = userService.getOneUserById(annualCreateRequest.getUserId()); //user kontrolü yapıp post ekleyeceğiz

        if (user == null)
            return null;

        AnnualPermission toSave = new AnnualPermission();
        toSave.setPermissionId(annualCreateRequest.getPermissionId());
        toSave.setContactPersonName(annualCreateRequest.getContactPersonName());
        toSave.setContactPerson(annualCreateRequest.getContactPerson());
        toSave.setTravelLocation(annualCreateRequest.getTravelLocation());
        toSave.setType(annualCreateRequest.getType());
        toSave.setStartDate(annualCreateRequest.getStartDate());
        toSave.setEndDate(annualCreateRequest.getEndDate());
        toSave.setApprovalStatus(annualCreateRequest.getApprovalStatus());

        toSave.setUser(user);
        return annualPermissionRepository.save(toSave);
    }

    @Override
    public List<AnnualPermissionResponse> getUserAnnualPermissions(Long userId) {
        return annualPermissionRepository.findByUser_Id(userId);
    }

    @Override
    public List<AnnualPermissionResponse> getAllAnnualPermissions() {
        List<AnnualPermission> list;
        list = annualPermissionRepository.findAll();
        return list.stream().map(annual_permissions -> new AnnualPermissionResponse(annual_permissions)).collect(Collectors.toList());
    }
}
