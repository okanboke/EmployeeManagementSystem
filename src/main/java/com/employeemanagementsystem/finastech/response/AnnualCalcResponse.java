package com.employeemanagementsystem.finastech.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnnualCalcResponse {

    private Long restDay;
    private Long restDayCalc;
    private String errorMessage;
}
