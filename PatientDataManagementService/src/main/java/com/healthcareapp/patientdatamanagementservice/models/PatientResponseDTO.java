package com.healthcareapp.patientdatamanagementservice.models;

import com.healthcareapp.patientdatamanagementservice.enums.Gender;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PatientResponseDTO {
    private String firstName;
    private String lastName;
    private Gender gender;
    private String patientImage;
    private Integer age;
    private String email;
    private String phoneNo;
}
