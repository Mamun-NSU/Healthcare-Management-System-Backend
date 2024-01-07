package com.healthcareapp.communityportalservice;
import com.healthcareapp.communityportalservice.entities.Review;
import com.healthcareapp.communityportalservice.models.AddReviewDTO;
import com.healthcareapp.communityportalservice.repositories.ReviewRepository;
import com.healthcareapp.communityportalservice.services.implementations.ReviewServiceImpl;
import com.healthcareapp.communityportalservice.services.interfaces.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @Test
    void testGetAllReview() {

        List<Review> expectedReviews = List.of(new Review(), new Review(), new Review());
        when(reviewRepository.findAll()).thenReturn(expectedReviews);
        List<Review> actualReviews = reviewService.getAllReview();

        assertEquals(expectedReviews, actualReviews);
    }

    @Test
    void testFindByPatientId() {
        // Arrange
        String patientId = "testPatientId";
        List<Review> expectedReviews = List.of(new Review(), new Review());

        when(reviewRepository.findByPatientId(eq(patientId))).thenReturn(Optional.of(expectedReviews));

        // Act
        List<Review> actualReviews = reviewService.findByPatientId(patientId);

        // Assert
        assertEquals(expectedReviews, actualReviews);
    }
}
