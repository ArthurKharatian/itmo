package com.example.itmo.extended.service;

import com.example.itmo.extended.model.dto.request.UserInfoReq;
import com.example.itmo.extended.model.dto.response.UserInfoResp;

import java.util.List;

public interface UserService {  // для межмодульной архитектуры
    UserInfoResp addUser(UserInfoReq req);

    UserInfoResp getUser(Long id);

    UserInfoResp updateUser(Long id, UserInfoReq req);

    void deleteUser(Long id);

    List<UserInfoResp> getAllUsers();

    UserInfoResp getUser(String email, String lastName);
}
