package com.example.springbootkafkamicroservices.springbootnodejskafkamicroservices.dtos;

import lombok.*;

import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class UserDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;
}
