package com.healthcareapp.communityportalservice.services.interfaces;

import com.healthcareapp.communityportalservice.entities.ProgressCheck;

// ProgressCheckService.java
public interface ProgressCheckService {

    void insertOrUpdateProgressCheck(String patientId, ProgressCheck progressCheck);

    ProgressCheck getProgressById();

    String checkProgress(String patientId);
}
