package edu.skillbox.rest_api.controller;

import org.springframework.web.bind.annotation.RestController;

import edu.skillbox.rest_api.entity.User;
import edu.skillbox.rest_api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService u) {
        this.userService = u;
    }
    
    @PostMapping()
    public User save(@RequestBody User entity) {
        return userService.save(entity);
    }

    @DeleteMapping
    public void delete(@RequestParam String id) {
        
    }


}
