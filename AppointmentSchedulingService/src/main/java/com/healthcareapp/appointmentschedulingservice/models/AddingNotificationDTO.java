package com.healthcareapp.appointmentschedulingservice.models;
import com.healthcareapp.appointmentschedulingservice.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddingNotificationDTO {
    private String userId;
    private NotificationType notificationType;
    private String message;
}

