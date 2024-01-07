package com.healthcareapp.patientdatamanagementservice.repositories;

import com.healthcareapp.patientdatamanagementservice.entities.HealthData;
import com.healthcareapp.patientdatamanagementservice.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface HealthDataRepository extends JpaRepository<HealthData, UUID> {
    Optional<HealthData> findByPatient(Patient patient);
}
