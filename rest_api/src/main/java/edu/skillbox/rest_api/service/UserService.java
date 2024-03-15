package edu.skillbox.rest_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.skillbox.rest_api.entity.User;
import edu.skillbox.rest_api.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository u) {
        this.userRepository = u;
    }
    
    public User save(User user) {
        return userRepository.save(user);
    }
}
