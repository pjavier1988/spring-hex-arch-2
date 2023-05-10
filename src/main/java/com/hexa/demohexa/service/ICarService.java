package com.hexa.demohexa.service;

import com.hexa.demohexa.domain.models.Car;

import java.util.List;

public interface ICarService {

    Car createCar(Car car);

    List<Car> findAllCars();

    Car findCarById(Long id);
}
