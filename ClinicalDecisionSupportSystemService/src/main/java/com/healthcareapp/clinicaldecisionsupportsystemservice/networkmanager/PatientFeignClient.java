package com.healthcareapp.clinicaldecisionsupportsystemservice.networkmanager;
import com.healthcareapp.clinicaldecisionsupportsystemservice.models.PatientResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name="patientdatamanagement-app",configuration = FeignClientConfiguration.class)
public interface PatientFeignClient {
    @GetMapping("/patients/get-by-id/{patientId}")
    ResponseEntity<PatientResponseDTO> findPatientById(@PathVariable String patientId);
}
