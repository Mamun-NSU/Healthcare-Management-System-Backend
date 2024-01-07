package com.healthcareapp.clinicaldecisionsupportsystemservice.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrentHealthRequest {
    private Double highBP;
    private Double lowBP;
    private Double heartRate;
    private String symptoms;
}
