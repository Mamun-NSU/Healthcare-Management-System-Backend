package com.healthcareapp.appointmentschedulingservice.models;
import com.healthcareapp.appointmentschedulingservice.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponseDTO {
    private String firstName;
    private String lastName;
    private Gender gender;
    private String email;
    private String phoneNo;
    private String patientImage;
    private Integer age;
    private String bloodGroup;
    private Date dateOfBirth;
}
