package com.healthcareapp.gptservice.dto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@RequiredArgsConstructor
public class PatientClinicalResponse {
    private String diagnoses;
    private String treatmentPlan;
    private String medication;
    private LocalDateTime generatedTime;
}
