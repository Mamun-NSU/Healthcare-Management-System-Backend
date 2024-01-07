package com.healthcareapp.communityportalservice.services.interfaces;

import com.healthcareapp.communityportalservice.entities.Review;
import com.healthcareapp.communityportalservice.models.AddReviewDTO;

import java.util.List;

public interface ReviewService {
    void addReview(String patientId, AddReviewDTO addReviewDTO);
    List<Review> getAllReview();
    List<Review> findByPatientId(String patientId);
}
