package com.healthcareapp.notificationandalertservice.repositories;

import com.healthcareapp.notificationandalertservice.entities.Notification;
import com.healthcareapp.notificationandalertservice.enums.NotificationStatus;
import com.healthcareapp.notificationandalertservice.services.interfaces.NotificationService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {
    List<Notification> findByUserIdAndStatus(String userId, NotificationStatus status);
    List<Notification> findByStatus(NotificationStatus status);
}
