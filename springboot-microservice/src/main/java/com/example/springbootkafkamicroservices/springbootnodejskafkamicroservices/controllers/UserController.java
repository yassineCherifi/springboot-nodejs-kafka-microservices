package com.example.springbootkafkamicroservices.springbootnodejskafkamicroservices.controllers;

import com.example.springbootkafkamicroservices.springbootnodejskafkamicroservices.dtos.UserDTO;
import com.example.springbootkafkamicroservices.springbootnodejskafkamicroservices.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


/*    @GetMapping(path = "/publish/{topicNumber}")
    public String publishMessage(@PathVariable String topicNumber) {
        System.out.println("Message sent to topic" + topicNumber);
        kafkaSender.sendMessage("topic" + topicNumber, "send message throuh topic " + topicNumber);
        return "Message sent";
    }*/

    @GetMapping(path = "")
    public ResponseEntity<List<UserDTO>> getAllUsers() {

        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
}
