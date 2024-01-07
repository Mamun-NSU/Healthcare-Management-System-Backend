package com.healthcareapp.clinicaldecisionsupportsystemservice.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientClinicalResponse {
    private String diagnoses;
    private String treatmentPlan;
    private String medication;
    private LocalDateTime generatedTime;
}
