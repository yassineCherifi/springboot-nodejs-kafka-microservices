package com.example.springbootkafkamicroservices.springbootnodejskafkamicroservices.services;

import com.example.springbootkafkamicroservices.springbootnodejskafkamicroservices.dtos.UserDTO;
import com.example.springbootkafkamicroservices.springbootnodejskafkamicroservices.exceptions.NotFoundException;
import com.example.springbootkafkamicroservices.springbootnodejskafkamicroservices.mappers.UserMapper;
import com.example.springbootkafkamicroservices.springbootnodejskafkamicroservices.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final KafkaSender kafkaSender;

    private final UserMapper userMapper;

    /**
     * get all users.
     *
     * @return List of user
     */
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    /**
     * get user by id.
     *
     * @param id
     * @return User
     */
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    UserDTO userDto = userMapper.userToUserDto(user);
                    kafkaSender.sendMessage("topic1",userDto);
                    return userDto  ;
                })
                .orElseThrow(() -> new NotFoundException("error.user.not-found"));
    }
}
