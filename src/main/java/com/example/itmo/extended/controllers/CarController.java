package com.example.itmo.extended.controllers;

import com.example.itmo.extended.model.dto.request.CarInfoReq;
import com.example.itmo.extended.model.dto.request.UserInfoReq;
import com.example.itmo.extended.model.dto.response.CarInfoResp;
import com.example.itmo.extended.model.dto.response.UserInfoResp;
import com.example.itmo.extended.service.CarService;
import com.example.itmo.extended.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService userService;

    @GetMapping("/{id}")
    public CarInfoResp getCar(@PathVariable Long id) {
        return userService.getCar(id);
    }

    @PostMapping
    public CarInfoResp addCar(@RequestBody CarInfoReq req) {
        return userService.addCar(req);
    }

    @PutMapping("/{id}")
    public CarInfoResp updateCar(@PathVariable Long id, @RequestBody CarInfoReq req) {
        return userService.updateCar(id, req);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        userService.deleteCar(id);
    }

    @GetMapping("/all")
    public List<CarInfoResp> getAllCars() {
        return userService.getAllCars();
    }

    @GetMapping("/all/{userId}")
    public List<CarInfoResp> getAllCars(@PathVariable Long userId) {
        return userService.getUserCars(userId);
    }

    @PostMapping("/linkUserAndDriver/{carId}/{userId}")
    public CarInfoResp linkUserAndDriver(@PathVariable Long carId, @PathVariable Long userId) {
        return userService.linkCarAndDriver(carId, userId);
    }

}
