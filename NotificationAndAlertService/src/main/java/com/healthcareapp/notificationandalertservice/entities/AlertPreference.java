package com.healthcareapp.notificationandalertservice.entities;

import com.healthcareapp.notificationandalertservice.enums.NotificationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "AlertPreference")
public class AlertPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "preference_id", updatable = false, nullable = false)
    private UUID preferenceId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_type", nullable = false)
    private NotificationType notificationType;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    // Constructors, getters, and setters
}

