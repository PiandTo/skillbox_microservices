package edu.skillbox.rest_api.controller;

import org.springframework.web.bind.annotation.RestController;

import edu.skillbox.rest_api.entity.User;
import edu.skillbox.rest_api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService u) {
        this.userService = u;
    }

    @Operation(summary = "Get all users")
    @GetMapping("/all")
    public List<User> getAll() {
        return userService.findAll();
    }
    
    @Operation(summary = "Save new user")
    @PostMapping()
    public User save(@RequestBody User entity) {
        return userService.save(entity);
    }

    @Operation(summary = "Delete user by id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        if (id == null) return;
        userService.deleteById(UUID.fromString(id));
    }


}
