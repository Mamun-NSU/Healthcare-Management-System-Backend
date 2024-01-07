package com.healthcareapp.authenticationservice.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
    private String userId;
    private String email;
    private String password;
}
