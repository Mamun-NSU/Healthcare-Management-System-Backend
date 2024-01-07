package com.healthcareapp.communityportalservice.services.implementations;

import com.healthcareapp.communityportalservice.entities.Review;
import com.healthcareapp.communityportalservice.models.AddReviewDTO;
import com.healthcareapp.communityportalservice.repositories.ReviewRepository;
import com.healthcareapp.communityportalservice.services.interfaces.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void addReview(String patientId, AddReviewDTO addReviewDTO) {
        Review review = new Review();
        review.setPatientId(addReviewDTO.getPatientId());
        review.setRating(addReviewDTO.getRating());
        review.setComment(addReviewDTO.getComment());

        reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReview() {
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> findByPatientId(String patientId) {
        return reviewRepository.findByPatientId(patientId).orElse(null);
    }
}

