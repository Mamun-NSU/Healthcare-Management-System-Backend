package com.healthcareapp.appointmentschedulingservice.networkmanager;


import com.healthcareapp.appointmentschedulingservice.models.DoctorResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

//@FeignClient(name="doctor",url = "http://localhost:8300",configuration = FeignClientConfiguration.class)
@FeignClient(name="doctordatamanagement-app",configuration = FeignClientConfiguration.class)
public interface DoctorFeignClient {
    @GetMapping("/doctors/get-by-id/{doctorId}")
    ResponseEntity<DoctorResponseDTO> findDoctorById(@PathVariable String doctorId);
}
