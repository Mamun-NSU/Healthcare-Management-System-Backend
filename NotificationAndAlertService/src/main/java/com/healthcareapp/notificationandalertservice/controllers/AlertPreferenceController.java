package com.healthcareapp.notificationandalertservice.controllers;

import com.healthcareapp.notificationandalertservice.entities.AlertPreference;
import com.healthcareapp.notificationandalertservice.excepitons.NotFoundException;
import com.healthcareapp.notificationandalertservice.models.AddingAlertPreferenceDTO;
import com.healthcareapp.notificationandalertservice.models.SuccessResponse;
import com.healthcareapp.notificationandalertservice.services.interfaces.AlertPreferenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/alert-preferences")
public class AlertPreferenceController {

    private final AlertPreferenceService alertPreferenceService;

    public AlertPreferenceController(AlertPreferenceService alertPreferenceService) {
        this.alertPreferenceService = alertPreferenceService;
    }

    @PostMapping("/add")
    public ResponseEntity<SuccessResponse> addAlertPreference(@RequestBody AddingAlertPreferenceDTO preferenceDTO) {
        try {
            alertPreferenceService.addAlertPreference(preferenceDTO);
            SuccessResponse successResponse = new SuccessResponse("Alert preference added successfully");
            return new ResponseEntity<>(successResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            // Exception handling is done by GlobalExceptionHandler
            throw e;
        }
    }

    @PutMapping("/update")
    public ResponseEntity<SuccessResponse> updateAlertPreference(@RequestBody AddingAlertPreferenceDTO updatedPreferenceDTO) {
        try {
            alertPreferenceService.updateAlertPreference(updatedPreferenceDTO);
            SuccessResponse successResponse = new SuccessResponse("Alert preference updated successfully");
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        } catch (Exception e) {
            // Exception handling is done by GlobalExceptionHandler
            throw e;
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<AlertPreference>> getAllAlertPreferences() {
        try {
            List<AlertPreference> preferences = alertPreferenceService.getAllAlertPreferences();
            return new ResponseEntity<>(preferences, HttpStatus.OK);
        } catch (Exception e) {
            // Exception handling is done by GlobalExceptionHandler
            throw e;
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AlertPreference>> getAlertPreferencesByUserId(@PathVariable String userId) {
        try {
            List<AlertPreference> preferences = alertPreferenceService.getAlertPreferencesByUserId(userId);
            return new ResponseEntity<>(preferences, HttpStatus.OK);
        } catch (NotFoundException e) {
            // Exception handling is done by GlobalExceptionHandler
            throw e;
        }
    }

    @DeleteMapping("/delete/{preferenceId}")
    public ResponseEntity<SuccessResponse> deleteAlertPreference(@PathVariable UUID preferenceId) {
        try {
            alertPreferenceService.deleteAlertPreference(preferenceId);
            SuccessResponse successResponse = new SuccessResponse("Alert preference deleted successfully");
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        } catch (NotFoundException e) {
            // Exception handling is done by GlobalExceptionHandler
            throw e;
        }
    }
}

