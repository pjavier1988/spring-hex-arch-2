package com.hexa.demohexa.repository;

import com.hexa.demohexa.common.exception.ObjectNotFoundException;
import com.hexa.demohexa.domain.models.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarDAO implements ICarDAO{

    @Autowired
    private CarRepository carRepository;
    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).
                orElseThrow(()->new ObjectNotFoundException("Id no encontrado: "+id));
    }
}
