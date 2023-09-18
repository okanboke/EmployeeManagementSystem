package com.employeemanagementsystem.projectx.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnnualCalcResponse {

    private int restDay;
    private int restDayCalc;
    private String errorMessage;
}
