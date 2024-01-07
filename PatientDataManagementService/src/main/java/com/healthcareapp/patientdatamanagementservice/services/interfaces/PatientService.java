package com.healthcareapp.patientdatamanagementservice.services.interfaces;

import com.healthcareapp.patientdatamanagementservice.entities.Patient;
import com.healthcareapp.patientdatamanagementservice.models.PatientRequestDTO;
import com.healthcareapp.patientdatamanagementservice.models.PatientResponseDTO;

import java.util.List;

public interface PatientService {
    void registerPatient(PatientRequestDTO patientRequestDTO);
    List<Patient> getAllPatients();
    PatientResponseDTO getPatientById(String patientId);
}
