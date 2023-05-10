package com.hexa.demohexa.common.payloads;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse <T>{
    private int code;
    private String message;
    private T data;
}
