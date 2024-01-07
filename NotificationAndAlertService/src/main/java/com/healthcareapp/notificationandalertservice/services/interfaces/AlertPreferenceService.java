package com.healthcareapp.notificationandalertservice.services.interfaces;

import com.healthcareapp.notificationandalertservice.entities.AlertPreference;
import com.healthcareapp.notificationandalertservice.models.AddingAlertPreferenceDTO;

import java.util.List;
import java.util.UUID;


public interface AlertPreferenceService {
    void addAlertPreference(AddingAlertPreferenceDTO preferenceDTO);
    void updateAlertPreference(AddingAlertPreferenceDTO updatedPreferenceDTO);
    List<AlertPreference> getAlertPreferencesByUserId(String userId);
    void deleteAlertPreference(UUID preferenceId);
    List<AlertPreference> getAllAlertPreferences();
}

