package com.example.springbootkafkamicroservices.springbootkafkamicroservices.config;

import com.example.springbootkafkamicroservices.springbootkafkamicroservices.metier.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/kafka")
public class controller {

    @Autowired
    KafkaSender kafkaSender;

    @GetMapping(path="/publish/{topicNumber}")
    public String publishMessage(@PathVariable String topicNumber)
    {
        System.out.println("Message sent to topic"+topicNumber);
        kafkaSender.sendMessage("topic"+topicNumber,"send message throuh topic "+topicNumber);
        return "Message sent";
    }
}
