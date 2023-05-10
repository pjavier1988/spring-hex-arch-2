package com.hexa.demohexa.common.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor

public class ErrorDetails extends ApiResponse{

    public ErrorDetails(){
        super();
    }
    private LocalDateTime timeStamp;
    private String details;
}
