package com.employeemanagementsystem.finastech.service.impl;

import com.employeemanagementsystem.finastech.entity.AnnualPermission;
import com.employeemanagementsystem.finastech.entity.User;
import com.employeemanagementsystem.finastech.repository.AnnualPermissionRepository;
import com.employeemanagementsystem.finastech.repository.UserRepository;
import com.employeemanagementsystem.finastech.service.AnnualPermissionService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Calendar;


@Service
public class AnnualPermissionServiceImpl implements AnnualPermissionService {

    private final AnnualPermissionRepository annualPermissionRepository;

    private final UserRepository userRepository;

    public AnnualPermissionServiceImpl(AnnualPermissionRepository annualPermissionRepository,
                                       UserRepository userRepository) {
        this.annualPermissionRepository = annualPermissionRepository;
        this.userRepository = userRepository;
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
}
