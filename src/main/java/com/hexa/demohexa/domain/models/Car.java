package com.hexa.demohexa.domain.models;



import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Car {
    @Id
    private Long carId;

    private String model;

    private int year;

    private double km;

}
