package com.healthcareapp.patientdatamanagementservice.controllers;

import com.healthcareapp.patientdatamanagementservice.entities.Patient;
import com.healthcareapp.patientdatamanagementservice.models.PatientRequestDTO;
import com.healthcareapp.patientdatamanagementservice.models.PatientResponseDTO;
import com.healthcareapp.patientdatamanagementservice.services.interfaces.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;
    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerPatient(@RequestBody PatientRequestDTO patientRequestDTO) {
            patientService.registerPatient(patientRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Patient registration successful");
    }
    @GetMapping("/all")
    public ResponseEntity<List<Patient>> getAllPatients() {
            List<Patient> patients = patientService.getAllPatients();
            return ResponseEntity.ok(patients);
    }
    @GetMapping("/get-by-id/{patientId}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable String patientId) {
        PatientResponseDTO patient = patientService.getPatientById(patientId);
        return ResponseEntity.ok(patient);
    }
}
