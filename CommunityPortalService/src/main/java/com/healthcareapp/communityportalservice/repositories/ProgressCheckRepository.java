package com.healthcareapp.communityportalservice.repositories;

import com.healthcareapp.communityportalservice.entities.ProgressCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

// ProgressCheckRepository.java
@Repository
public interface ProgressCheckRepository extends JpaRepository<ProgressCheck, UUID> {
    ProgressCheck findByPatientId(String patientId);
}

