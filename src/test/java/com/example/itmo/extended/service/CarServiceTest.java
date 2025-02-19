package com.example.itmo.extended.service;

import com.example.itmo.extended.model.db.entity.User;
import com.example.itmo.extended.model.db.repository.CarRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {
    @InjectMocks
    private CarService carService;

    @Mock
    private UserService userService;

    @Mock
    private CarRepository carRepository;

    @Spy
    private ObjectMapper objectMapper;

    @Test
    public void getCar() {
    }

    @Test
    public void addCar() {
    }

    @Test
    public void updateCar() {
    }

    @Test
    public void deleteCar() {
    }

    @Test
    public void getAllCars() {
    }

    @Test
    public void linkCarAndDriver() {
        User user = new User();
        user.setId(1L);
        when(userService.getUserFromDB(1L)).thenReturn(user);
    }

    @Test
    public void getUserCars() {
    }
}