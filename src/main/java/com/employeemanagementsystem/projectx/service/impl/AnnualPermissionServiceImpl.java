package com.employeemanagementsystem.projectx.service.impl;

import com.employeemanagementsystem.projectx.entity.AnnualPermission;
import com.employeemanagementsystem.projectx.entity.User;
import com.employeemanagementsystem.projectx.exception.AnnualExpception;
import com.employeemanagementsystem.projectx.repository.AnnualPermissionRepository;
import com.employeemanagementsystem.projectx.repository.UserRepository;
import com.employeemanagementsystem.projectx.request.AnnualCalcRequest;
import com.employeemanagementsystem.projectx.request.AnnualCreateRequest;
import com.employeemanagementsystem.projectx.request.UpdateAnnualRequest;
import com.employeemanagementsystem.projectx.response.AnnualCalcResponse;
import com.employeemanagementsystem.projectx.response.AnnualPermissionResponse;
import com.employeemanagementsystem.projectx.service.AnnualPermissionService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
            if (updateDateTime != null) {
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
        toSave.setErrorMessage("Talebiniz başarıyla gönderildi.");

        toSave.setUser(user);

        //mevcut izinden alınan izin çıkarılacak
        Long differenceInMillis = Math.abs(annualCreateRequest.getStartDate().getTime() - annualCreateRequest.getEndDate().getTime());
        Long daysDifference = TimeUnit.DAYS.convert(differenceInMillis, TimeUnit.MILLISECONDS);
        int daysDifferenceToInt = daysDifference.intValue();
        int restDayGetter = toSave.getUser().getRestDay();

        // Başlangıç ve bitiş tarihleri
        Date startDate = annualCreateRequest.getStartDate();
        Date endDate = annualCreateRequest.getEndDate();

        //Date convert LocalDate!
        Instant sInstant = startDate.toInstant();
        Instant eInstant = endDate.toInstant();
        LocalDate sLocalDate = sInstant.atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate eLocalDate = eInstant.atZone(ZoneId.systemDefault()).toLocalDate();


        // Resmi tatilleri ve bayramları içeren bir liste
        List<LocalDate> holidayList = new ArrayList<>();
        holidayList.add(LocalDate.of(2023, 1, 1)); // Yılbaşı
        holidayList.add(LocalDate.of(2023, 4, 21)); // Ramazan Bayramı
        holidayList.add(LocalDate.of(2023, 4, 22)); // Ramazan Bayramı
        holidayList.add(LocalDate.of(2023, 4, 23)); // Ramazan Bayramı / Çocuk Bayramı
        holidayList.add(LocalDate.of(2023, 4, 24)); // Ramazan Bayramı
        holidayList.add(LocalDate.of(2023, 5, 1)); // İşçi Bayramı
        holidayList.add(LocalDate.of(2023, 5, 19)); // Atatürk'ü anma ve gençlik spor bayramı
        holidayList.add(LocalDate.of(2023, 5, 28)); // Kurban Bayramı
        holidayList.add(LocalDate.of(2023, 5, 29)); // Kurban Bayramı
        holidayList.add(LocalDate.of(2023, 5, 30)); // Kurban Bayramı
        holidayList.add(LocalDate.of(2023, 5, 31)); // Kurban Bayramı
        holidayList.add(LocalDate.of(2023, 7, 15)); // Demokrasi Bayramı
        holidayList.add(LocalDate.of(2023, 8, 30)); // Zafer Bayramı
        holidayList.add(LocalDate.of(2023, 10, 29)); // Cumhuriyet Bayramı


        if (daysDifferenceToInt > restDayGetter && restDayGetter == 0) {
            toSave.setErrorMessage("İzin hakkınız bulunmuyor");
            throw new AnnualExpception(toSave.getErrorMessage());
            //return AnnualExpception; //hata mesajı dönülebilir
        } else {
            for (LocalDate tatil : holidayList) {
                if (tatil.isAfter(sLocalDate) && tatil.isBefore(eLocalDate)) {
                    daysDifferenceToInt--; // Tatiller çıkartılır
                }
            }
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
        User user = userService.getOneUserById(annualCalcRequest.getId()); //user kontrolü yapıp post ekleyeceğiz
        AnnualCalcResponse response = new AnnualCalcResponse();

        if (annualCalcRequest.getId() != null && annualCalcRequest.getStartDate() == null) {
            response.setRestDay(user.getRestDay());
            response.setErrorMessage(user.getRestDay() + " gün izniniz bulunmakta");
            return response;
        }
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
        int restDays = calc.intValue();
        for (LocalDate tatil : holidayList) {
            if (tatil.isAfter(startDate) && tatil.isBefore(endDate)) {
                restDays--; // Tatiller çıkartılır
            }
        }
        response.setRestDayCalc(restDays);
        response.setRestDay(user.getRestDay());
        response.setErrorMessage("Başarıyla Hesaplandı!");
        return response;
        //return new AnnualCalcResponse().setRestDayCalc(restDays);
    }
}
