package com.healthcareapp.notificationandalertservice.models;

import com.healthcareapp.notificationandalertservice.enums.NotificationType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AddingAlertPreferenceDTO {

    private String userId;
    private NotificationType notificationType;
    private boolean enabled;

    // Constructors, getters, and setters
}
