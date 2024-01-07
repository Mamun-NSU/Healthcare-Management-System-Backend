package com.healthcareapp.communityportalservice.models;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AddReviewDTO {
    private String patientId;
    private int rating;
    private String comment;
}
