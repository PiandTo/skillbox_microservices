package edu.skillbox.rest_api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.skillbox.rest_api.entity.User;

public interface UserRepository extends JpaRepository<User, UUID>{
    
}
