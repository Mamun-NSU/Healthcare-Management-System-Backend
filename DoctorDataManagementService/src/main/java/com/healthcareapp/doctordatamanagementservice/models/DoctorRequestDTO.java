package com.healthcareapp.doctordatamanagementservice.models;

import com.healthcareapp.doctordatamanagementservice.enums.Gender;
import com.healthcareapp.doctordatamanagementservice.enums.Qualifications;
import com.healthcareapp.doctordatamanagementservice.enums.Specialities;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class DoctorRequestDTO {
    private String dfirstName;
    private String dlastName;
    private Gender dgender;
    private String doctorImage;
    private String email;
    private String password;
    private Set<Specialities> specialities;
    private Set<Qualifications> qualifications;
}
