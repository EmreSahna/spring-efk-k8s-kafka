package com.emresahna.microserviceapp.kafka;

import com.emresahna.microserviceapp.dto.StudentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaListeners {
    @KafkaListener(topics = "student-topic", groupId = "groupId")
    void listener(StudentResponse response) {
        log.info("Listener received data : {}, ", response);
    }
}