package com.healthcareapp.notificationandalertservice.repositories;

import com.healthcareapp.notificationandalertservice.entities.AlertPreference;
import com.healthcareapp.notificationandalertservice.enums.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AlertPreferenceRepository extends JpaRepository<AlertPreference, UUID> {
   List<AlertPreference> findByUserIdAndEnabledIsTrue(String userId);
   AlertPreference findByUserIdAndNotificationType(String userId, NotificationType type);
   List<AlertPreference> findByUserId(String userId);
}

