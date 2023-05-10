package com.hexa.demohexa.domain.mappers;

import com.hexa.demohexa.domain.dto.CarDTO;
import com.hexa.demohexa.domain.models.Car;

public class CarMapper {

    public static CarDTO modelToDTO(Car car){
        CarDTO carDTO = CarDTO.builder()
                .year(car.getYear())
                .model(car.getModel())
                .km(car.getKm())
                .build();
        return carDTO;
    }

    public static Car dtoToModel(CarDTO carDto){
        Car car = Car.builder()
                .year(carDto.getYear())
                .model(carDto.getModel())
                .km(carDto.getKm())
                .build();
        return car;
    }
}
