package com.healthcareapp.doctordatamanagementservice.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class RegisterRequestDTO {
    private String userId;
    private String email;
    private String password;
}
