package com.healthcareapp.patientdatamanagementservice.services.interfaces;


import com.healthcareapp.patientdatamanagementservice.entities.HealthData;
import com.healthcareapp.patientdatamanagementservice.models.HealthRequestDTO;

public interface HealthDataService {
    void addHealthData(HealthRequestDTO healthRequestDTO);
    HealthData findHealthDataById();
    void updateHealthData(HealthRequestDTO healthRequestDTO);
}
