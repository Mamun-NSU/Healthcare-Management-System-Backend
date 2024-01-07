package com.healthcareapp.notificationandalertservice.entities;

import com.healthcareapp.notificationandalertservice .enums.NotificationStatus;
import com.healthcareapp.notificationandalertservice.enums.NotificationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.security.Timestamp;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "notification_id", updatable = false)
    private UUID notificationId;

    @Column(name = "user_id")
    @NotEmpty(message = "User id can not be empty")
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_type")
    @NotNull(message = "Notification type can not be empty")
    private NotificationType notificationType;

    @Column(name = "message")
    @NotEmpty(message = "Message can not be empty")
    private String message;

    @Column(name = "timestamp")
    private Date timestamp;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private NotificationStatus status;
}

