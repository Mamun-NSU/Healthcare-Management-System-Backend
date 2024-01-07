package com.healthcareapp.notificationandalertservice.services.implementations;
import com.healthcareapp.notificationandalertservice.entities.AlertPreference;
import com.healthcareapp.notificationandalertservice.entities.Notification;
import com.healthcareapp.notificationandalertservice.enums.NotificationStatus;
import com.healthcareapp.notificationandalertservice.enums.NotificationType;
import com.healthcareapp.notificationandalertservice.models.AddingNotificationDTO;
import com.healthcareapp.notificationandalertservice.repositories.AlertPreferenceRepository;
import com.healthcareapp.notificationandalertservice.repositories.NotificationRepository;
import com.healthcareapp.notificationandalertservice.services.interfaces.NotificationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final AlertPreferenceRepository alertPreferenceRepository;
    public NotificationServiceImpl(NotificationRepository notificationRepository, AlertPreferenceRepository alertPreferenceRepository) {
        this.notificationRepository = notificationRepository;
        this.alertPreferenceRepository = alertPreferenceRepository;
    }

    @Override
    public void addNotification(AddingNotificationDTO notificationDTO) {
        Notification newNotification = new Notification();
        newNotification.setNotificationType(NotificationType.APPOINTMENT_REMINDER);
        newNotification.setUserId(notificationDTO.getUserId());
        newNotification.setMessage(notificationDTO.getMessage());
        newNotification.setTimestamp(Date.from(Instant.now()));
        newNotification.setStatus(NotificationStatus.CREATED);
        notificationRepository.save(newNotification);
    }
    @Override
    public List<Notification> getNotificationsByUserIdAndStatus(String userId) {
        List<AlertPreference> enabledPreferences = alertPreferenceRepository.findByUserIdAndEnabledIsTrue(userId);
        List<Notification> notifications = notificationRepository.findByUserIdAndStatus(userId, NotificationStatus.UNREAD);

        return notifications.stream()
                .filter(notification -> isEnabledNotification(notification, enabledPreferences))
                .collect(Collectors.toList());
    }


    @Transactional
    @Override
    public void allowNotification() {
        List<Notification> notifications = notificationRepository.findByStatus(NotificationStatus.CREATED);
        for (Notification notification : notifications) {
            notification.setStatus(NotificationStatus.UNREAD);
        }
        notificationRepository.saveAll(notifications);
    }
    @Transactional
    @Override
    public void updateNotificationOfUser(String userId) {

        List<Notification> unreadNotifications = notificationRepository.findByUserIdAndStatus(userId, NotificationStatus.UNREAD);

        for (Notification notification : unreadNotifications) {
            notification.setStatus(NotificationStatus.READ);
        }
        notificationRepository.saveAll(unreadNotifications);
    }

    @Override
    public List<Notification> getAllCreatedNotifications() {
        return notificationRepository.findByStatus(NotificationStatus.CREATED);
    }
    private boolean isEnabledNotification(Notification notification, List<AlertPreference> enabledPreferences) {

        if (enabledPreferences.isEmpty()) {
            return true;
        }
        return enabledPreferences.stream()
                .anyMatch(preference -> preference.getNotificationType() == notification.getNotificationType());
    }
}