package com.example.itmo.extended.controllers;

import com.example.itmo.extended.model.dto.request.UserInfoReq;
import com.example.itmo.extended.model.dto.response.UserInfoResp;
import com.example.itmo.extended.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Пользователи")
@SecurityScheme(type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT", name = "Authorization")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    @Operation(summary = "Получить пользователя по id")
    public UserInfoResp getUser(@RequestHeader("api-key") String apiKey, @PathVariable Long id) {
        return userService.getUser(apiKey, id);
    }

    @PostMapping
    @Operation(summary = "Создать пользователя", security = @SecurityRequirement(name = AUTHORIZATION))
    public UserInfoResp addUser(@RequestBody @Valid UserInfoReq req) {
        return userService.addUser(req);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить даннные пользователя по id", security = @SecurityRequirement(name = AUTHORIZATION))
    public UserInfoResp updateUser(@PathVariable Long id, @RequestBody UserInfoReq req) {
        return userService.updateUser(id, req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить пользователя по id", security = @SecurityRequirement(name = AUTHORIZATION))
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }



//    @GetMapping
//    public UserInfoResp getUserWithParams(@RequestParam(required = false) String email, @RequestParam String lastName) {
//        return userService.getUser(email, lastName);
//    }

    @GetMapping("/all")
    @Operation(summary = "Получить список пользователей")
    public Page<UserInfoResp> getAllUsers(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer perPage,
                                          @RequestParam(defaultValue = "lastName") String sort,
                                          @RequestParam(defaultValue = "ASC") Sort.Direction order,
                                          @RequestParam(required = false) String filter

    ) {
        return userService.getAllUsers(page, perPage, sort, order, filter);
    }

    @GetMapping("/ya/{id}")
    @Operation(summary = "Получить пользователя по id")
    public UserInfoResp getYaUser(@RequestHeader("api-key") String apiKey, @PathVariable Long id) {
        return userService.getYaUser(id,apiKey);
    }
}
