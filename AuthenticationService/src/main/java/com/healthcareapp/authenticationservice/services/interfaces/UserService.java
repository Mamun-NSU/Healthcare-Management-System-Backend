package com.healthcareapp.authenticationservice.services.interfaces;

import com.healthcareapp.authenticationservice.entities.User;
import com.healthcareapp.authenticationservice.enums.Role;
import com.healthcareapp.authenticationservice.models.RegisterRequestDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    void addNewUser(RegisterRequestDTO registerRequestDTO);
    void addNewAdmin(RegisterRequestDTO registerRequestDTO);
    List<User> getAllUsers();
    Role getRole(String email);
}
