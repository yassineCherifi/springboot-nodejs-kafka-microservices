package com.example.springbootkafkamicroservices.springbootnodejskafkamicroservices.services;

import com.example.springbootkafkamicroservices.springbootnodejskafkamicroservices.dtos.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaSender {
    private final KafkaTemplate<String, UserDTO> kafkaTemplate;
    Logger logger = LoggerFactory.getLogger(KafkaSender.class);

    KafkaSender(KafkaTemplate<String, UserDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topicName, UserDTO userDTO) {
        kafkaTemplate.send(topicName, userDTO);
    }

    void sendMessageWithCallback(String topicName, UserDTO userDTO) {
        ListenableFuture<SendResult<String, UserDTO>> future =
                kafkaTemplate.send(topicName, userDTO);

        future.addCallback(new ListenableFutureCallback<SendResult<String, UserDTO>>() {
            @Override
            public void onSuccess(SendResult<String, UserDTO> result) {
                logger.info("Message [{}] delivered with offset {}",
                        userDTO,
                        result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.warn("Unable to deliver message [{}]. {}",
                        userDTO,
                        ex.getMessage());
            }
        });
    }
}
