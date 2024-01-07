package com.healthcareapp.patientdatamanagementservice.models;

import com.healthcareapp.patientdatamanagementservice.enums.BloodGroup;
import com.healthcareapp.patientdatamanagementservice.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class PatientRequestDTO {
    private String firstName;
    private String lastName;
    private Gender gender;
    private String email;
    private String patientImage;
    private Integer age;
    private String phoneNo;
    private String password;
    private BloodGroup bloodGroup;
    private Date dateOfBirth;
}

