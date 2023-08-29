package com.employeemanagementsystem.finastech.component;

import com.employeemanagementsystem.finastech.service.impl.AnnualPermissionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PermissionScheduledTasks {


    private final AnnualPermissionServiceImpl annualPermissionService;

    public PermissionScheduledTasks(AnnualPermissionServiceImpl annualPermissionService) {
        this.annualPermissionService = annualPermissionService;
    }

    @Scheduled(cron = "0 0 0 * * ?") // Her gün gece yarısında çalışır
    public void updateRestDays() {
        annualPermissionService.updateRestDaysForUsers();
    }
}
