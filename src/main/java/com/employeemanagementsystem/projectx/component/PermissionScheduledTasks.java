package com.employeemanagementsystem.projectx.component;

import com.employeemanagementsystem.projectx.service.impl.AnnualPermissionServiceImpl;
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
