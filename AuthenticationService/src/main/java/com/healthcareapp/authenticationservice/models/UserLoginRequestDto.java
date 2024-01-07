package com.healthcareapp.authenticationservice.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequestDto {
    private String email;
    private String password;
}
