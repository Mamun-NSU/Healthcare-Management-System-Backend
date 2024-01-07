package com.healthcareapp.notificationandalertservice.services.implementations;

import com.healthcareapp.notificationandalertservice.entities.AlertPreference;
import com.healthcareapp.notificationandalertservice.models.AddingAlertPreferenceDTO;
import com.healthcareapp.notificationandalertservice.repositories.AlertPreferenceRepository;
import com.healthcareapp.notificationandalertservice.services.interfaces.AlertPreferenceService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AlertPreferenceServiceImpl implements AlertPreferenceService {

    private final AlertPreferenceRepository alertPreferenceRepository;

    public AlertPreferenceServiceImpl(AlertPreferenceRepository alertPreferenceRepository) {
        this.alertPreferenceRepository = alertPreferenceRepository;
    }

    @Override
    public void addAlertPreference(AddingAlertPreferenceDTO preferenceDTO) {
        AlertPreference newPreference = new AlertPreference();
        BeanUtils.copyProperties(preferenceDTO, newPreference);
        alertPreferenceRepository.save(newPreference);
    }

    @Override
    public void updateAlertPreference(AddingAlertPreferenceDTO updatedPreferenceDTO) {
        AlertPreference existingPreference =  alertPreferenceRepository
                .findByUserIdAndNotificationType(updatedPreferenceDTO.getUserId(), updatedPreferenceDTO.getNotificationType());
        BeanUtils.copyProperties(updatedPreferenceDTO, existingPreference);
        alertPreferenceRepository.save(existingPreference);
    }

    @Override
    public List<AlertPreference> getAllAlertPreferences() {
        return alertPreferenceRepository.findAll();
    }

    @Override
    public List<AlertPreference> getAlertPreferencesByUserId(String userId) {
        return alertPreferenceRepository.findByUserId(userId);
    }

    @Override
    public void deleteAlertPreference(UUID preferenceId) {
        alertPreferenceRepository.deleteById(preferenceId);
    }


}

