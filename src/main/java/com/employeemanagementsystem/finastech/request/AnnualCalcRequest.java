package com.employeemanagementsystem.finastech.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnnualCalcRequest {


    private LocalDate startDate;
    private LocalDate endDate;

}
