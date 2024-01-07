package com.healthcareapp.notificationandalertservice;

import com.healthcareapp.notificationandalertservice.entities.AlertPreference;
import com.healthcareapp.notificationandalertservice.enums.NotificationType;
import com.healthcareapp.notificationandalertservice.models.AddingAlertPreferenceDTO;
import com.healthcareapp.notificationandalertservice.repositories.AlertPreferenceRepository;
import com.healthcareapp.notificationandalertservice.services.implementations.AlertPreferenceServiceImpl;
import com.healthcareapp.notificationandalertservice.services.interfaces.AlertPreferenceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlertPreferenceServiceImplTest {

    @Mock
    private AlertPreferenceRepository alertPreferenceRepository;
    @InjectMocks
    private AlertPreferenceServiceImpl alertPreferenceService;

    @Test
    void testAddAlertPreference() {

        AddingAlertPreferenceDTO preferenceDTO = new AddingAlertPreferenceDTO();
        preferenceDTO.setUserId("testUserId");
        preferenceDTO.setNotificationType(NotificationType.APPOINTMENT_REMINDER);
        alertPreferenceService.addAlertPreference(preferenceDTO);
        verify(alertPreferenceRepository).save(any(AlertPreference.class));
    }
    @Test
    void testUpdateAlertPreference() {
        // Arrange
        AddingAlertPreferenceDTO updatedPreferenceDTO = new AddingAlertPreferenceDTO();
        updatedPreferenceDTO.setUserId("testUserId");
        updatedPreferenceDTO.setNotificationType(NotificationType.POST_REMINDER);
        AlertPreference existingPreference = new AlertPreference();
        existingPreference.setUserId("testUserId");
        existingPreference.setNotificationType(NotificationType.POST_REMINDER);
        when(alertPreferenceRepository.findByUserIdAndNotificationType(any(), any())).thenReturn(existingPreference);
        alertPreferenceService.updateAlertPreference(updatedPreferenceDTO);
        verify(alertPreferenceRepository).save(existingPreference);
        assertEquals(updatedPreferenceDTO.getUserId(), existingPreference.getUserId());
        assertEquals(updatedPreferenceDTO.getNotificationType(), existingPreference.getNotificationType());
    }
}

