package com.healthcareapp.doctordatamanagementservice.models;

import com.healthcareapp.doctordatamanagementservice.enums.Qualifications;
import com.healthcareapp.doctordatamanagementservice.enums.Specialities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorFilterRequestDTO {
    private Set<Specialities> specialities;
    private Set<Qualifications> qualifications;
}

