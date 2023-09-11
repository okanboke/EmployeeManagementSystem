package com.employeemanagementsystem.finastech.service.impl;

import com.employeemanagementsystem.finastech.entity.AnnualPermission;
import com.employeemanagementsystem.finastech.entity.JustificationPermission;
import com.employeemanagementsystem.finastech.entity.User;
import com.employeemanagementsystem.finastech.exception.AnnualExpception;
import com.employeemanagementsystem.finastech.repository.AnnualPermissionRepository;
import com.employeemanagementsystem.finastech.repository.UserRepository;
import com.employeemanagementsystem.finastech.request.AnnualCalcRequest;
import com.employeemanagementsystem.finastech.request.AnnualCreateRequest;
import com.employeemanagementsystem.finastech.request.UpdateAnnualRequest;
import com.employeemanagementsystem.finastech.request.UpdatePermissionRequest;
import com.employeemanagementsystem.finastech.response.AnnualCalcResponse;
import com.employeemanagementsystem.finastech.response.AnnualPermissionResponse;
import com.employeemanagementsystem.finastech.response.AnnualPermissionResponseModel;
import com.employeemanagementsystem.finastech.service.AnnualPermissionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service
public class AnnualPermissionServiceImpl implements AnnualPermissionService {

    private final AnnualPermissionRepository annualPermissionRepository;

    private final UserRepository userRepository;

    private final UserServiceImpl userService;

    public AnnualPermissionServiceImpl(AnnualPermissionRepository annualPermissionRepository,
                                       UserRepository userRepository,
                                       UserServiceImpl userService) {
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

        //mevcut izinden alınan izin çıkarılacak
        Long differenceInMillis = Math.abs(annualCreateRequest.getStartDate().getTime() - annualCreateRequest.getEndDate().getTime());
        Long daysDifference = TimeUnit.DAYS.convert(differenceInMillis, TimeUnit.MILLISECONDS);
        int daysDifferenceToInt = daysDifference.intValue();
        int restDayGetter = toSave.getUser().getRestDay();

        if(daysDifferenceToInt > restDayGetter && restDayGetter == 0) {
            toSave.setErrorMessage("İzin hakkınız bulunmuyor");
            throw new AnnualExpception(toSave.getErrorMessage());
            //return AnnualExpception; //hata mesajı dönülebilir
        }
        else {
            toSave.getUser().setRestDay(restDayGetter - daysDifferenceToInt);
            return annualPermissionRepository.save(toSave);
        }
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

    //for admin izin onayı
    //koşul eklenecek
    @Override
    public AnnualPermission updatePermissionStatus(UpdateAnnualRequest updatePermissionRequest) {
        return annualPermissionRepository.findById(updatePermissionRequest.getPermissionId())
        .map(annual_permissions -> {
            annual_permissions.setApprovalStatus(updatePermissionRequest.isApprovalStatus());
            return annualPermissionRepository.save(annual_permissions);
        })
                .orElseGet(() -> {
                    return null;
                });
    }

    @Override
    public AnnualCalcResponse annualCalculate(AnnualCalcRequest annualCalcRequest) {
        // Başlangıç ve bitiş tarihleri
        LocalDate startDate = annualCalcRequest.getStartDate();
        LocalDate endDate = annualCalcRequest.getEndDate();

        // Resmi tatilleri ve bayramları içeren bir liste
        List<LocalDate> holidayList = new ArrayList<>();
        holidayList.add(LocalDate.of(2023, 1, 1)); // Yılbaşı
        holidayList.add(LocalDate.of(2023, 4, 21)); // Ramazan Bayramı
        holidayList.add(LocalDate.of(2023, 4, 22)); // Ramazan Bayramı
        holidayList.add(LocalDate.of(2023, 4, 23)); // Ramazan Bayramı / Çocuk Bayramı
        holidayList.add(LocalDate.of(2023, 4, 24)); // Ramazan Bayramı
        holidayList.add(LocalDate.of(2023, 5, 1)); // İşçi Bayramı
        holidayList.add(LocalDate.of(2023, 5, 19)); // Atatürkü anma ve gençlik spor bayramı
        holidayList.add(LocalDate.of(2023, 5, 28)); // Kurban Bayramı
        holidayList.add(LocalDate.of(2023, 5, 29)); // Kurban Bayramı
        holidayList.add(LocalDate.of(2023, 5, 30)); // Kurban Bayramı
        holidayList.add(LocalDate.of(2023, 5, 31)); // Kurban Bayramı
        holidayList.add(LocalDate.of(2023, 7, 15)); // Demokrasi Bayramı
        holidayList.add(LocalDate.of(2023, 8, 30)); // Zafer Bayramı
        holidayList.add(LocalDate.of(2023, 10, 29)); // Cumhuriyet Bayramı

        Long calc = ChronoUnit.DAYS.between(annualCalcRequest.getStartDate(), annualCalcRequest.getEndDate());
        Long restDays = calc;
        for (LocalDate tatil : holidayList) {
            if (tatil.isAfter(startDate) && tatil.isBefore(endDate)) {
                restDays--; // Tatiller çıkartılır
            }
        }
        AnnualCalcResponse response = new AnnualCalcResponse();
        response.setRestDayCalc(restDays);
        return response;
        //return new AnnualCalcResponse().setRestDayCalc(restDays);
    }
}
