package com.healthcareapp.authenticationservice.controllers;

import com.healthcareapp.authenticationservice.models.RegisterRequestDTO;
import com.healthcareapp.authenticationservice.services.interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
        userService.addNewAdmin(registerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully");
    }
}
