package com.example.itmo.extended.service.impl;

import com.example.itmo.extended.model.dto.request.UserInfoReq;
import com.example.itmo.extended.model.dto.response.UserInfoResp;
import com.example.itmo.extended.model.enums.Gender;
import com.example.itmo.extended.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final ObjectMapper mapper;

    @Override
    public UserInfoResp addUser(UserInfoReq req) {
        UserInfoResp userInfoResp = mapper.convertValue(req, UserInfoResp.class);
        userInfoResp.setId(1L);
        return userInfoResp;
    }

    @Override
    public UserInfoResp getUser(Long id) {
        return UserInfoResp.builder()
                .id(1L)
                .email("test@test.ru")
                .age(40)
                .firstName("Ivan")
                .lastName("Ivanov")
                .middleName("Ivanovich")
                .gender(Gender.MALE)
                .password("12345")
                .build();
    }

    @Override
    public UserInfoResp updateUser(Long id, UserInfoReq req) {
        if (id != 1L) {
            log.error("User with id {} not found", id);
            return null;
        }

        return UserInfoResp.builder()
                .id(1L)
                .email("itmo@test.ru")
                .age(40)
                .firstName("Petr")
                .lastName("Petrov")
                .middleName("Petrovich")
                .gender(Gender.MALE)
                .password("****")
                .build();
    }

    @Override
    public void deleteUser(Long id) {
        if (id != 1L) {
            log.error("User with id {} not found", id);
            return;
        }
        log.info("User with id {} deleted", id);
    }

    @Override
    public List<UserInfoResp> getAllUsers() {
        return List.of(UserInfoResp.builder()
                .id(1L)
                .email("itmo@test.ru")
                .age(40)
                .firstName("Petr")
                .lastName("Petrov")
                .middleName("Petrovich")
                .gender(Gender.MALE)
                .password("****")
                .build());
    }

    @Override
    public UserInfoResp getUser(String email, String lastName) {
        return getUser(1L);
    }

}
