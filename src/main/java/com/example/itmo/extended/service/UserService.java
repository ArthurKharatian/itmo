package com.example.itmo.extended.service;

import com.example.itmo.extended.model.db.entity.User;
import com.example.itmo.extended.model.dto.request.UserInfoReq;
import com.example.itmo.extended.model.dto.response.UserInfoResp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface UserService {  // для межмодульной архитектуры
    UserInfoResp addUser(UserInfoReq req);

    UserInfoResp getUser(Long id);

    User getUserFromDB(Long id);

    UserInfoResp updateUser(Long id, UserInfoReq req);

    void deleteUser(Long id);

    Page<UserInfoResp> getAllUsers(Integer page, Integer perPage, String sort, Sort.Direction order, String filter);

    User updateCarList(User userFromDB);
}
