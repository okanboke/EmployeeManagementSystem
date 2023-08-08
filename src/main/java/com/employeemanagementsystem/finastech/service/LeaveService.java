package com.employeemanagementsystem.finastech.service;

import com.employeemanagementsystem.finastech.entity.Leaves;
import com.employeemanagementsystem.finastech.request.LeaveCreateRequest;

public interface LeaveService {
    Leaves createOneLeave(LeaveCreateRequest leaveCreateRequest);
}
