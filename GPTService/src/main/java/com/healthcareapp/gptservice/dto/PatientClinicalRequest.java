package com.healthcareapp.gptservice.dto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@RequiredArgsConstructor
public class PatientClinicalRequest {
    private String gender;
    private Integer age;
    private String bloodPressure;
    private String heartRate;
    private String symptoms;
}

