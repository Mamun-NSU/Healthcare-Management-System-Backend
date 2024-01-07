package com.healthcareapp.appointmentschedulingservice.models;


import com.healthcareapp.appointmentschedulingservice.enums.Qualifications;
import com.healthcareapp.appointmentschedulingservice.enums.Specialities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponseDTO {
    private String dfirstName;
    private String dlastName;
    private String phoneNumber;
    private String email;
    private String gender;
    private String doctorImage;
    private Set<Qualifications> qualifications;
    private Set<Specialities> specialities;
}
