package com.example.itmo.extended.service.impl;

import com.example.itmo.extended.model.db.entity.User;
import com.example.itmo.extended.model.db.repository.UserRepository;
import com.example.itmo.extended.model.dto.request.UserInfoReq;
import com.example.itmo.extended.model.dto.response.UserInfoResp;
import com.example.itmo.extended.model.enums.UserStatus;
import com.example.itmo.extended.service.UserService;
import com.example.itmo.extended.utils.PaginationUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final ObjectMapper mapper;
    private final UserRepository userRepository;
//    private final CarRepository carRepository; - некорректно
//    private final CarService carService;  ошибка с цикличной зависимостью

    @Override
    public UserInfoResp addUser(UserInfoReq req) {
        User user = mapper.convertValue(req, User.class);
        user.setStatus(UserStatus.CREATED);

        User save = userRepository.save(user);
        return mapper.convertValue(save, UserInfoResp.class);
    }

    @Override
    public UserInfoResp getUser(Long id) {
        User user = getUserFromDB(id);
//        if (optionalUser.isPresent()) {
//            return mapper.convertValue(optionalUser.get(), UserInfoResp.class);
//        }
//
//        if (optionalUser.isEmpty()) {
//            return null;
//        }


        return mapper.convertValue(user, UserInfoResp.class);
    }

    @Override
    public User getUserFromDB(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(new User());
    }

    @Override
    public UserInfoResp updateUser(Long id, UserInfoReq req) {
        User userFromDB = getUserFromDB(id);
        if (userFromDB.getId() == null) {
            return mapper.convertValue(userFromDB, UserInfoResp.class);
        }

        User userReq = mapper.convertValue(req, User.class);

        userFromDB.setEmail(userReq.getEmail() == null ? userFromDB.getEmail() : userReq.getEmail());
        userFromDB.setPassword(userReq.getPassword() == null ? userFromDB.getPassword() : userReq.getPassword());
        userFromDB.setFirstName(userReq.getFirstName() == null ? userFromDB.getFirstName() : userReq.getFirstName());
        userFromDB.setLastName(userReq.getLastName() == null ? userFromDB.getLastName() : userReq.getLastName());
        userFromDB.setMiddleName(userReq.getMiddleName() == null ? userFromDB.getMiddleName() : userReq.getMiddleName());
        userFromDB.setAge(userReq.getAge() == null ? userFromDB.getAge() : userReq.getAge());
        userFromDB.setGender(userReq.getGender() == null ? userFromDB.getGender() : userReq.getGender());


        userFromDB = userRepository.save(userFromDB);
        return mapper.convertValue(userFromDB, UserInfoResp.class);
    }

    @Override
    public void deleteUser(Long id) {
        User userFromDB = getUserFromDB(id);
        if (userFromDB.getId() == null) {
            log.error("User with id {} not found", id);
            return;
        }

        userFromDB.setStatus(UserStatus.DELETED);
        userRepository.save(userFromDB);
//        userRepository.deleteById(id);
    }

    @Override
    public Page<UserInfoResp> getAllUsers(Integer page, Integer perPage, String sort, Sort.Direction order, String filter) {

        Pageable pageRequest = PaginationUtils.getPageRequest(page, perPage, sort, order);

        Page<User> users;

        if (StringUtils.hasText(filter)) {
            users = userRepository.findAllFiltered(pageRequest, filter);
        } else {
            users = userRepository.findAll(pageRequest);
        }

        List<UserInfoResp> content = users.getContent().stream()
                .map(u -> mapper.convertValue(u, UserInfoResp.class))
                .collect(Collectors.toList());

        return new PageImpl<>(content, pageRequest, users.getTotalElements());
    }


    @Override
    public User updateCarList(User updatedUser) {
        return userRepository.save(updatedUser);
    }



}
