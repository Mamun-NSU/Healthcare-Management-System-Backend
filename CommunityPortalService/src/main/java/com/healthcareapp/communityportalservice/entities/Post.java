package com.healthcareapp.communityportalservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id", updatable = false, nullable = false)
    private UUID postId;

    @Column(name = "patient_id")
    private String patientId;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "post_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date postTime;

    // Constructors, getters, setters, and other methods...
}

