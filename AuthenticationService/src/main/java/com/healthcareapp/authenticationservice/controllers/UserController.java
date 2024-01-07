package com.healthcareapp.authenticationservice.controllers;

import com.healthcareapp.authenticationservice.entities.User;
import com.healthcareapp.authenticationservice.models.RegisterRequestDTO;
import com.healthcareapp.authenticationservice.services.interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
        userService.addNewUser(registerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
}

