package com.healthcareapp.clinicaldecisionsupportsystemservice.models;
import com.healthcareapp.clinicaldecisionsupportsystemservice.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    private Integer age;
}
