package com.healthcareapp.notificationandalertservice.services.interfaces;

import com.healthcareapp.notificationandalertservice.entities.Notification;
import com.healthcareapp.notificationandalertservice.models.AddingNotificationDTO;

import java.util.List;
import java.util.UUID;

public interface NotificationService {
    void addNotification(AddingNotificationDTO notificationDTO);
    List<Notification> getNotificationsByUserIdAndStatus(String userId);
    void allowNotification();
    void updateNotificationOfUser(String userId);
    List<Notification> getAllCreatedNotifications();
}

