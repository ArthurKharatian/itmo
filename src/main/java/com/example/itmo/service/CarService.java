package com.example.itmo.service;

import com.example.itmo.model.dto.request.CarInfoRequest;
import com.example.itmo.model.dto.request.UserInfoRequest;
import com.example.itmo.model.dto.response.CarInfoResponse;
import com.example.itmo.model.dto.response.UserInfoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CarService {
    CarInfoResponse createCar(CarInfoRequest request);

    CarInfoResponse getCar(Long id);

    CarInfoResponse updateCar(Long id, CarInfoRequest request);

    void deleteCar(Long id);

    List<CarInfoResponse> getAllCars();

    CarInfoResponse linkCarAndDriver(Long userId, Long carId);

    Page<CarInfoResponse> getUserCars(Long userId, Integer page, Integer perPage, String sort, Sort.Direction order);
}
