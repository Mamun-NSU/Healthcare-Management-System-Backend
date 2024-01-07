package com.healthcareapp.notificationandalertservice.models;

import com.healthcareapp.notificationandalertservice.enums.NotificationStatus;
import com.healthcareapp.notificationandalertservice.enums.NotificationType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddingNotificationDTO {
    private String userId;
    private NotificationType notificationType;
    private String message;
}


