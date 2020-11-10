package com.example.springbootkafkamicroservices.springbootkafkamicroservices.metier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerExample {

    @KafkaListener(topics = "topic1",
            groupId = "reflectoring-group-1")
    void listenerOne(String data) {
        System.out.println(data);
    }

    @KafkaListener(topics = "topic2",
            groupId = "reflectoring-group-1")
    void listenerTwo(String data) {
        System.out.println("Message received from topic 2!");
    }
}
