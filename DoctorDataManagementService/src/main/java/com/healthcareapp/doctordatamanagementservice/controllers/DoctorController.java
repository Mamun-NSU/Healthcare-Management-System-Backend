package com.healthcareapp.doctordatamanagementservice.controllers;

import com.healthcareapp.doctordatamanagementservice.entities.Doctor;
import com.healthcareapp.doctordatamanagementservice.models.*;
import com.healthcareapp.doctordatamanagementservice.services.interfaces.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/register")
    public ResponseEntity<SuccessResponse> addNewDoctor(@RequestBody DoctorRequestDTO doctorRequestDTO) {
        doctorService.addNewDoctor(doctorRequestDTO);

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("Doctor added successfully");

        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }
    @GetMapping("/get-by-id/{doctorId}")
    public ResponseEntity<DoctorResponseDTO> getDoctorById(@PathVariable String doctorId) {
        return new ResponseEntity<>(doctorService.getDoctorById(doctorId), HttpStatus.OK);
    }
    @PostMapping({"/filter"})
    public ResponseEntity<List<Doctor>> getDoctorByFiltering(@RequestBody DoctorFilterRequestDTO doctorFilterRequestDTO) {
        return new ResponseEntity(this.doctorService.getDoctorsByFiltering(doctorFilterRequestDTO), HttpStatus.OK);
    }
    @PutMapping("/update-doctor")
    public ResponseEntity<SuccessResponse> updateDoctor(@RequestBody DoctorRequestDTO doctorRequestDTO) {
        doctorService.updateDoctorData(doctorRequestDTO);

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("Doctor data updated successfully.");

        return ResponseEntity.ok(successResponse);
    }
}

