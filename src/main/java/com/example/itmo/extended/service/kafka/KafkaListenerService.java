package com.example.itmo.extended.service.kafka;

import com.example.itmo.extended.model.db.entity.Car;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaListenerService {

    @KafkaListener(topics = "${kafka.topics.notification:itmo}")
    public void eventListener(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                              @Payload String message) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.readValue(message, Car.class);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }

    }

//    @KafkaListener(topics = "topic")
    public void anotherEventListener(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                              @Payload String message) {

    }
}
