package com.healthcareapp.patientdatamanagementservice.models;
import com.healthcareapp.patientdatamanagementservice.enums.Allergie;
import com.healthcareapp.patientdatamanagementservice.enums.DiabetesStatus;
import com.healthcareapp.patientdatamanagementservice.enums.SugarLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class HealthRequestDTO {
    private Double height;
    private Double weight;
    private Double highBloodPressure;
    private Double lowBloodPressure;
    private Double heartRate;
    private SugarLevel sugarLevel;
    private DiabetesStatus diabetesStatus;
    private Allergie allergie;
}
