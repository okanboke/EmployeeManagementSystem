package com.employeemanagementsystem.projectx.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshRequest {

    private Long userId;
    private String refreshToken;
}
