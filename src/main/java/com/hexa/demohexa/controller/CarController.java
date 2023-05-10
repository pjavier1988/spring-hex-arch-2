package com.hexa.demohexa.controller;

import com.hexa.demohexa.common.payloads.ApiResponse;
import com.hexa.demohexa.domain.dto.CarDTO;
import com.hexa.demohexa.domain.mappers.CarMapper;
import com.hexa.demohexa.domain.models.Car;
import com.hexa.demohexa.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private ICarService iCarService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllCars(){
        List<Car> listCars = iCarService.findAllCars();
        List<CarDTO> listCarsDTO = listCars.stream().map(CarMapper::modelToDTO)
                .collect(Collectors.toList());

        ApiResponse<List<CarDTO>> response = ApiResponse.<List<CarDTO>>builder()
                .code(0)
                .message("")
                .data(listCarsDTO)
                .build();


        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @GetMapping("/{carId}")
    public ResponseEntity<ApiResponse> getCarById(@PathVariable(name = "carId") Long carId){
        Car carFound = iCarService.findCarById(carId);
        CarDTO carFoundDTO = CarMapper.modelToDTO(carFound);
        ApiResponse<CarDTO> response = ApiResponse.<CarDTO>builder()
                .code(0)
                .message("")
                .data(carFoundDTO)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> insertCar(@RequestBody CarDTO carDTO){
        Car car = CarMapper.dtoToModel(carDTO);
        Car carCreated = iCarService.createCar(car);
        CarDTO carCreatedDTO = CarMapper.modelToDTO(carCreated);
        ApiResponse<CarDTO> response = ApiResponse.<CarDTO>builder()
                .code(0)
                .message("Carro creado exitosamente")
                .data(carCreatedDTO)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
}
