package com.example.springbootkafkamicroservices.springbootnodejskafkamicroservices.repositories;

import com.example.springbootkafkamicroservices.springbootnodejskafkamicroservices.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
