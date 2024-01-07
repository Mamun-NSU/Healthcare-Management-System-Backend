package com.healthcareapp.authenticationservice.models;

import com.healthcareapp.authenticationservice.enums.Role;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private Role role;
}
