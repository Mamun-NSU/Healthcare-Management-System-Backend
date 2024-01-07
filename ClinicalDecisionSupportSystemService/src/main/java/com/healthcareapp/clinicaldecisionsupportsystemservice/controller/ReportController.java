package com.healthcareapp.clinicaldecisionsupportsystemservice.controller;
import com.healthcareapp.clinicaldecisionsupportsystemservice.entities.Report;
import com.healthcareapp.clinicaldecisionsupportsystemservice.models.CurrentHealthRequest;
import com.healthcareapp.clinicaldecisionsupportsystemservice.services.interfaces.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reportService;
    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }
   @PostMapping("/get-support")
    public ResponseEntity<Report> getReportByPatient(@RequestBody CurrentHealthRequest currentHealthRequest) {
        Report report = reportService.getReportByPatient(currentHealthRequest);
        return ResponseEntity.ok(report);
    }
}

