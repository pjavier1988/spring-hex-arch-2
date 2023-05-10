package com.hexa.demohexa.repository;

import com.hexa.demohexa.domain.models.Car;

import java.util.List;

public interface ICarDAO {

    Car save(Car car);

    List<Car> findAll();

    Car findById(Long id);
}
