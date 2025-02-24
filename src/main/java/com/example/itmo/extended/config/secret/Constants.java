package com.example.itmo.extended.config.secret;

import com.example.itmo.extended.exception.CommonBackendException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
    private static final String API_KEY = "SmF2YSBEZXZlbG9wZXI=";

    public static void validateKey(String apiKey) {
        if (!apiKey.equals(API_KEY)) {
            throw new CommonBackendException("Неверный ключ сервиса", HttpStatus.FORBIDDEN);
        }
    }
}
