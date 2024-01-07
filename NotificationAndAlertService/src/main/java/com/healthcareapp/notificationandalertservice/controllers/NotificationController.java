package com.healthcareapp.notificationandalertservice.controllers;

import com.healthcareapp.notificationandalertservice.entities.Notification;
import com.healthcareapp.notificationandalertservice.models.AddingNotificationDTO;
import com.healthcareapp.notificationandalertservice.models.SuccessResponse;
import com.healthcareapp.notificationandalertservice.services.interfaces.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    @GetMapping("/user/{userId}/status/unread")
    public ResponseEntity<List<Notification>> getUnreadNotificationsByUserId(@PathVariable String userId) {
        List<Notification> unreadNotifications = notificationService.getNotificationsByUserIdAndStatus(userId);
        return new ResponseEntity<>(unreadNotifications, HttpStatus.OK);
    }
    @PutMapping("/allow")
    public ResponseEntity<SuccessResponse> allowNotification() {
        notificationService.allowNotification();
        SuccessResponse successResponse = new SuccessResponse("Notifications allowed successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(successResponse);
    }

    @PostMapping("/add")
    public ResponseEntity<SuccessResponse> addNotification(@RequestBody AddingNotificationDTO notificationDTO) {
        notificationService.addNotification(notificationDTO);
        SuccessResponse successResponse = new SuccessResponse("Notification added successfully");
        return new ResponseEntity<>(successResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<SuccessResponse> updateNotificationOfUser(@PathVariable String userId) {
        notificationService.updateNotificationOfUser(userId);
        SuccessResponse successResponse = new SuccessResponse("Notifications for user " + userId + " updated successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(successResponse);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Notification>> getAllCreatedNotifications() {
        List<Notification> notifications = notificationService.getAllCreatedNotifications();
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }
}

