package com.healthcareapp.patientdatamanagementservice.repositories;

import com.healthcareapp.patientdatamanagementservice.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, String> {

}
