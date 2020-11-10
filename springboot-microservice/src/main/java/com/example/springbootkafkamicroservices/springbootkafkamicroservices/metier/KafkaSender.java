package com.example.springbootkafkamicroservices.springbootkafkamicroservices.metier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.beans.BeanProperty;

@Service
public class KafkaSender {
    private final KafkaTemplate<String, String> kafkaTemplate;
    Logger logger = LoggerFactory.getLogger(KafkaSender.class);

    KafkaSender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topicName, String message) {
        kafkaTemplate.send(topicName, message);
    }

    void sendMessageWithCallback(String message, String topicName) {
        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(topicName, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("Message [{}] delivered with offset {}",
                        message,
                        result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.warn("Unable to deliver message [{}]. {}",
                        message,
                        ex.getMessage());
            }
        });
    }
}
