package com.hexa.demohexa.service;

import com.hexa.demohexa.domain.models.Car;
import com.hexa.demohexa.repository.CarDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @InjectMocks
    CarService carService;

    @Mock
    CarDAO carDAO;

    @Test
    void shouldReturnMessageOfFoundCarGivenAValidId(){
        Long expectedId = 1L;
        String expectedMessage = "Encontre carro con id "+expectedId;

        Car car = Car.
                builder()
                .carId(expectedId)
                .model("Modelo")
                .year(2020)
                .km(100)
                .build();

        when(carDAO.findById(anyLong())).thenReturn(car);

        String message = carService.carExists(expectedId);

        assertThat(message).isEqualTo(expectedMessage);
    }

    @Test
    void shouldReturnMessageOfNotFoundCarGivenANullObjectCar(){
        Long expectedId = 1L;
        String expectedMessage = "No encontre un carro";



        when(carDAO.findById(anyLong())).thenReturn(null);

        String message = carService.carExists(expectedId);

        assertThat(message).isEqualTo(expectedMessage);
    }
}
