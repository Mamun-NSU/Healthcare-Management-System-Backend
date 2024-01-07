package com.healthcareapp.clinicaldecisionsupportsystemservice.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientClinicalRequest {
    private String gender;
    private Integer age;
    private String bloodPressure;
    private String heartRate;
    private String symptoms;
}
