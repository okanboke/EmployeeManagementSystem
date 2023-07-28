package com.employeemanagementsystem.finastech.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshRequest {

    private Long userId;
    private String refreshToken;
}
