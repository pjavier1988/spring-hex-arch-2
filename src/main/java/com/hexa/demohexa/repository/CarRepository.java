package com.hexa.demohexa.repository;

import com.hexa.demohexa.domain.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Long> {

    List<Car> findByYearAndModel(int year, String model);
}
