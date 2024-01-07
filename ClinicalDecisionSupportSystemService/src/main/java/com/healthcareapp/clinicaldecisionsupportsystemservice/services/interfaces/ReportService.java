package com.healthcareapp.clinicaldecisionsupportsystemservice.services.interfaces;
import com.healthcareapp.clinicaldecisionsupportsystemservice.entities.Report;
import com.healthcareapp.clinicaldecisionsupportsystemservice.models.CurrentHealthRequest;
public interface ReportService {
    Report getReportByPatient(CurrentHealthRequest currentHealthRequest);
}

