package com.healthcareapp.communityportalservice.controllers;

import com.healthcareapp.communityportalservice.entities.ProgressCheck;
import com.healthcareapp.communityportalservice.models.SuccessResponse;
import com.healthcareapp.communityportalservice.services.interfaces.ProgressCheckService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/progress")
public class ProgressCheckController {
    private final ProgressCheckService progressCheckService;

    public ProgressCheckController(ProgressCheckService progressCheckService) {
        this.progressCheckService = progressCheckService;
    }

    @PostMapping("/update/{patientId}")
    public ResponseEntity<SuccessResponse> updateProgressCheck(
            @PathVariable String patientId,
            @RequestBody ProgressCheck progressCheck) {
        progressCheckService.insertOrUpdateProgressCheck(patientId, progressCheck);
        SuccessResponse successResponse = new SuccessResponse("Progress check updated successfully.");
        return ResponseEntity.ok(successResponse);
    }

    @GetMapping("/check/{patientId}")
    public ResponseEntity<SuccessResponse> checkProgress(@PathVariable String patientId) {
        String progressMessage = progressCheckService.checkProgress(patientId);
        SuccessResponse responseMessage = new SuccessResponse(progressMessage);
        return ResponseEntity.ok(responseMessage);
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<ProgressCheck> getProgressById() {
        return ResponseEntity.ok(progressCheckService.getProgressById());
    }
}

