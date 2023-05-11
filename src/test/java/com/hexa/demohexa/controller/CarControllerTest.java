package com.hexa.demohexa.controller;

import com.hexa.demohexa.common.exception.ObjectNotFoundException;
import com.hexa.demohexa.domain.models.Car;
import com.hexa.demohexa.repository.CarDAO;
import com.hexa.demohexa.service.CarService;
import com.hexa.demohexa.service.ICarService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;

@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ICarService iCarService;
    @InjectMocks
    CarService carService;
    @Mock
    CarDAO carDAO;
    @Test
    public void shouldRetriveHttpStatusOKAndAListOfTwoCarsWhenPerformFindAllGetRequests() throws Exception {

        Car car = Car.
                builder()
                .carId(1L)
                .model("Modelo")
                .year(2020)
                .km(100)
                .build();

        Car car2 = Car.
                builder()
                .carId(1L)
                .model("Modelo2")
                .year(2021)
                .km(100)
                .build();
        List<Car> listOfCars = new ArrayList<>();
        listOfCars.add(car);
        listOfCars.add(car2);
        when(iCarService.findAllCars()).thenAnswer(invocation -> listOfCars);
        ResultActions resultActions = mockMvc.perform(get("/cars"));
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.data.size()",is(listOfCars.size())))
                .andExpect(jsonPath("$.data.[0].model", is("Modelo")));
    }

    @Test
    public void shouldRetriveHttpStatusOKAndACarObjectWhenPerformFindByIdGetRequests() throws Exception {
        Long givenId = 1L;
        Car car = Car.
                builder()
                .carId(givenId)
                .model("Modelo")
                .year(2020)
                .km(100)
                .build();

        when(iCarService.findCarById(anyLong())).thenAnswer(invocation -> car);
        ResultActions resultActions = mockMvc.perform(get("/cars/"+givenId));
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.data.model", is("Modelo")));
    }


    @Test
    public void shouldRetriveHttpStatusNotFoundWhenPerformFindByIdGetRequests() throws Exception {
        Long givenId = 1L;
        Car car = Car.
                builder()
                .carId(givenId)
                .model("Modelo")
                .year(2020)
                .km(100)
                .build();

        when(iCarService.findCarById(anyLong())).thenThrow(new ObjectNotFoundException("Id no encontrado"));
        ResultActions resultActions = mockMvc.perform(get("/cars/"+givenId));
        resultActions.andExpect(status().isNotFound())
                .andDo(print());
    }
}
