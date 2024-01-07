package com.healthcareapp.clinicaldecisionsupportsystemservice.networkmanager;
import com.healthcareapp.clinicaldecisionsupportsystemservice.models.PatientClinicalRequest;
import com.healthcareapp.clinicaldecisionsupportsystemservice.models.PatientClinicalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="gpt-app",url = "http://localhost:8010/bot/chat",configuration = FeignClientConfiguration.class)
public interface GptFeignClient {
    @PostMapping
    ResponseEntity<PatientClinicalResponse> findPatientReport(@RequestBody PatientClinicalRequest patientClinicalRequest);
}
