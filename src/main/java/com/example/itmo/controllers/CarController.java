package com.example.itmo.controllers;

import com.example.itmo.model.dto.request.CarInfoRequest;
import com.example.itmo.model.dto.response.CarInfoResponse;
import com.example.itmo.service.CarService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
@SecurityRequirement(name = AUTHORIZATION)
public class CarController {

    private final CarService carService;

    @PostMapping
    public CarInfoResponse createCar(@RequestBody CarInfoRequest request) {
        return carService.createCar(request);
    }

    @GetMapping("/{id}")
    public CarInfoResponse getCar(@PathVariable Long id) {
        return carService.getCar(id);
    }

    @PutMapping("/{id}")
    public CarInfoResponse updateCar(@PathVariable Long id, @RequestBody CarInfoRequest request) {
        return carService.updateCar(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }

    @GetMapping("/all")
    public List<CarInfoResponse> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping("/linkCarAndDriver/{userId}/{carId}")
    public CarInfoResponse linkCarAndDriver(@PathVariable Long userId, @PathVariable Long carId) {
        return carService.linkCarAndDriver(userId, carId);
    }

}
