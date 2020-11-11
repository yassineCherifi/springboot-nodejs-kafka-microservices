package com.example.springbootkafkamicroservices.springbootnodejskafkamicroservices.services;

import com.example.springbootkafkamicroservices.springbootnodejskafkamicroservices.dtos.UserDTO;
import com.example.springbootkafkamicroservices.springbootnodejskafkamicroservices.mappers.UserMapper;
import com.example.springbootkafkamicroservices.springbootnodejskafkamicroservices.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }
}
