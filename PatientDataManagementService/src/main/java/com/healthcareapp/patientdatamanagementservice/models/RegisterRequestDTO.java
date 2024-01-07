package com.healthcareapp.patientdatamanagementservice.models;

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
