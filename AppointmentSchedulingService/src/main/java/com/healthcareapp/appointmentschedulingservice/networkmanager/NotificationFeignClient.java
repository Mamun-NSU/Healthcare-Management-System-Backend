package com.healthcareapp.appointmentschedulingservice.networkmanager;
import com.healthcareapp.appointmentschedulingservice.models.AddingNotificationDTO;
import com.healthcareapp.appointmentschedulingservice.models.SuccessResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="notificationandalert-app",configuration = FeignClientConfiguration.class)
public interface NotificationFeignClient {
    @PostMapping("/notifications/add")
    ResponseEntity<SuccessResponse> createNewNotification(@RequestBody AddingNotificationDTO addingNotificationDTO);
}