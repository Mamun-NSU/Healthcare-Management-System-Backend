package com.healthcareapp.notificationandalertservice;

import com.healthcareapp.notificationandalertservice.entities.AlertPreference;
import com.healthcareapp.notificationandalertservice.entities.Notification;
import com.healthcareapp.notificationandalertservice.enums.NotificationStatus;
import com.healthcareapp.notificationandalertservice.enums.NotificationType;
import com.healthcareapp.notificationandalertservice.models.AddingNotificationDTO;
import com.healthcareapp.notificationandalertservice.repositories.AlertPreferenceRepository;
import com.healthcareapp.notificationandalertservice.repositories.NotificationRepository;
import com.healthcareapp.notificationandalertservice.services.implementations.NotificationServiceImpl;
import com.healthcareapp.notificationandalertservice.services.interfaces.NotificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceImplTest {
    @Mock
    private NotificationRepository notificationRepository;
    @Mock
    private AlertPreferenceRepository alertPreferenceRepository;
    @InjectMocks
    private NotificationServiceImpl notificationService;
    @Test
    void testAddNotification() {
        // Arrange
        AddingNotificationDTO notificationDTO = new AddingNotificationDTO();
        notificationDTO.setUserId("testUserId");
        notificationDTO.setMessage("Test Message");
        notificationService.addNotification(notificationDTO);
        verify(notificationRepository).save(any(Notification.class));
    }

    @Test
    void testGetNotificationsByUserIdAndStatus() {
        // Arrange
        String userId = "testUserId";
        List<AlertPreference> enabledPreferences = new ArrayList<>();
        List<Notification> notifications = new ArrayList<>();
        when(alertPreferenceRepository.findByUserIdAndEnabledIsTrue(eq(userId))).thenReturn(enabledPreferences);
        when(notificationRepository.findByUserIdAndStatus(eq(userId), eq(NotificationStatus.UNREAD))).thenReturn(notifications);

        List<Notification> result = notificationService.getNotificationsByUserIdAndStatus(userId);

        verify(notificationRepository).findByUserIdAndStatus(eq(userId), eq(NotificationStatus.UNREAD));
        assertEquals(notifications, result);
    }

}