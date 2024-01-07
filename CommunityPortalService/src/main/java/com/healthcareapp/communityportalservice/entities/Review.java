package com.healthcareapp.communityportalservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "review")
@Getter
@Setter
@RequiredArgsConstructor
public class Review {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "review_id")
    private UUID reviewId;
    @Column(name = "patient_id")
    private String patientId;
    @Column(name = "rating")
    private int rating;
    @Column(name = "comment")
    private String comment;
}
