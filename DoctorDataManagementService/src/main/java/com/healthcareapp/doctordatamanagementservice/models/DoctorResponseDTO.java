package com.healthcareapp.doctordatamanagementservice.models;

import com.healthcareapp.doctordatamanagementservice.enums.Qualifications;
import com.healthcareapp.doctordatamanagementservice.enums.Specialities;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class DoctorResponseDTO {
    private String dfirstName;
    private String dlastName;
    private String dgender;
    private String doctorImage;
    private Set<Qualifications> qualifications;
    private Set<Specialities> specialities;
}
