package com.hexa.demohexa.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarDTO {
    private String model;
    private int year;
    private double km;
}
