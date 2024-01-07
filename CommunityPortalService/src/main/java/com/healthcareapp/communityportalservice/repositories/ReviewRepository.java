package com.healthcareapp.communityportalservice.repositories;

import com.healthcareapp.communityportalservice.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    Optional<List<Review>> findByPatientId(String patientId);
}
