package com.example.itmo.extended.service;

import com.example.itmo.extended.model.db.entity.Car;
import com.example.itmo.extended.model.db.entity.User;
import com.example.itmo.extended.model.db.repository.CarRepository;
import com.example.itmo.extended.model.dto.request.CarInfoReq;
import com.example.itmo.extended.model.dto.response.CarInfoResp;
import com.example.itmo.extended.model.dto.response.UserInfoResp;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {
    private final UserService userService;
    private final CarRepository carRepository;
    private final ObjectMapper objectMapper;

    public CarInfoResp getCar(Long id) {
        return null;
    }

    public CarInfoResp addCar(CarInfoReq req) {
        return null;
    }

    public CarInfoResp updateCar(Long id, CarInfoReq req) {
        return null;
    }

    public void deleteCar(Long id) {
    }

    public List<CarInfoResp> getAllCars() {
        return null;
    }

    private Car getCarFromDB(Long carId) {
        return carRepository.findById(carId).orElse(new Car());
    }

    public CarInfoResp linkCarAndDriver(Long carId, Long userId) {
        Car carFromDB = getCarFromDB(carId);
        User userFromDB = userService.getUserFromDB(userId);

        if (carFromDB.getId() == null || userFromDB.getId() == null) {
            return CarInfoResp.builder().build();
        }

        List<Car> cars = userFromDB.getCars();

        Car existingCar = cars.stream().filter(car -> car.getId().equals(carId)).findFirst().orElse(null);

        if (existingCar != null) {
            return objectMapper.convertValue(existingCar, CarInfoResp.class);
        }

        cars.add(carFromDB);
        User user = userService.updateCarList(userFromDB);

        carFromDB.setUser(user);
        carRepository.save(carFromDB);

        CarInfoResp carInfoResp = objectMapper.convertValue(carFromDB, CarInfoResp.class);
        UserInfoResp userInfoResp = objectMapper.convertValue(user, UserInfoResp.class);

        carInfoResp.setUser(userInfoResp);

        return carInfoResp;
    }

    public List<CarInfoResp> getUserCars(Long userId) {
        return carRepository.getUserCarsBrandNames(userId).stream()
                .map(car -> objectMapper.convertValue(car, CarInfoResp.class))
                .collect(Collectors.toList());
    }
}
