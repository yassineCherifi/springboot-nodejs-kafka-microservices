package com.example.springbootkafkamicroservices.springbootnodejskafkamicroservices.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerExample {

    @KafkaListener(topics = "topic1",
            groupId = "mcs-kafka-group")
    void listenerOne(String data) {
        System.out.println(data);
    }

    @KafkaListener(topics = "topic2",
            groupId = "mcs-kafka-group")
    void listenerTwo(String data) {
        System.out.println("Message received from topic 2!");
    }
}
