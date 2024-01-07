package com.healthcareapp.doctordatamanagementservice.services.interfaces;

import com.healthcareapp.doctordatamanagementservice.entities.Doctor;
import com.healthcareapp.doctordatamanagementservice.models.DoctorFilterRequestDTO;
import com.healthcareapp.doctordatamanagementservice.models.DoctorRequestDTO;
import com.healthcareapp.doctordatamanagementservice.models.DoctorResponseDTO;
import com.healthcareapp.doctordatamanagementservice.models.SpecialityQueryDTO;

import java.util.List;

public interface DoctorService {
    void addNewDoctor(DoctorRequestDTO doctorRequestDTO);

    List<Doctor> getAllDoctors();

    void updateDoctorData(DoctorRequestDTO addDoctorDTO);


    DoctorResponseDTO getDoctorById(String doctorId);

    List<Doctor> getDoctorsByFiltering(DoctorFilterRequestDTO doctorFilterRequestDTO);
}