package com.healthcareapp.patientdatamanagementservice.controllers;

import com.healthcareapp.patientdatamanagementservice.entities.HealthData;
import com.healthcareapp.patientdatamanagementservice.models.HealthRequestDTO;
import com.healthcareapp.patientdatamanagementservice.services.interfaces.HealthDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health")
public class HealthDataController {

    private final HealthDataService healthDataService;

    @Autowired
    public HealthDataController(HealthDataService healthDataService) {
        this.healthDataService = healthDataService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addHealthData(@RequestBody HealthRequestDTO healthRequestDTO) {
        healthDataService.addHealthData(healthRequestDTO);
        return new ResponseEntity<>("Health data added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public ResponseEntity<HealthData> findHealthDataById() {
        HealthData healthData = healthDataService.findHealthDataById();
        return new ResponseEntity<>(healthData, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateHealthData(@RequestBody HealthRequestDTO healthRequestDTO) {
        healthDataService.updateHealthData(healthRequestDTO);
        return new ResponseEntity<>("Health data updated successfully", HttpStatus.OK);
    }
}

