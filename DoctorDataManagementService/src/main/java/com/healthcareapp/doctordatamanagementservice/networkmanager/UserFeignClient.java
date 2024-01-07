package com.healthcareapp.doctordatamanagementservice.networkmanager;

import com.healthcareapp.doctordatamanagementservice.models.RegisterRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authentication-app")
public interface UserFeignClient {
    @PostMapping("/users/register")
    ResponseEntity<String> registerUser(@RequestBody RegisterRequestDTO registerRequestDTO);
}
