package com.hexa.demohexa.service;

import com.hexa.demohexa.domain.models.Car;
import com.hexa.demohexa.repository.ICarDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService implements ICarService{

    @Autowired
    private ICarDAO iCarDAO;

    @Override
    public Car createCar(Car car) {
        return iCarDAO.save(car);
    }

    @Override
    public List<Car> findAllCars() {
        return iCarDAO.findAll();
    }

    @Override
    public Car findCarById(Long id) {
        return iCarDAO.findById(id);
    }
}
