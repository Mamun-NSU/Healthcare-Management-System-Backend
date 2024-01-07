package com.healthcareapp.communityportalservice.controllers;

import com.healthcareapp.communityportalservice.entities.Review;
import com.healthcareapp.communityportalservice.models.AddReviewDTO;
import com.healthcareapp.communityportalservice.services.interfaces.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/add/{patientId}")
    public ResponseEntity<String> addReview(@PathVariable String patientId, @RequestBody AddReviewDTO addReviewDTO) {
        reviewService.addReview(patientId, addReviewDTO);
        return new ResponseEntity<>("Review added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReview();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/find/{patientId}")
    public ResponseEntity<List<Review>> findReviewByPatientId(@PathVariable String patientId) {
        List<Review> review = reviewService.findByPatientId(patientId);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }
}

