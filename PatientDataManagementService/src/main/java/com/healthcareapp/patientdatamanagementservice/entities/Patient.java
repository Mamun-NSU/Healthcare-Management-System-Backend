package com.healthcareapp.patientdatamanagementservice.entities;


import com.healthcareapp.patientdatamanagementservice.enums.BloodGroup;
import com.healthcareapp.patientdatamanagementservice.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "patient")
public class Patient {
    @Id
    @Column(name = "patient_id", updatable = false, nullable = false)
    private String patientId;
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "Phone_no")
    private String phoneNo;

    @Column(name = "image_path")
    private String patientImage;

    @Column(name = "age")
    private Integer age;

    @Column(name = "blood_group")
    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    public Patient() {
        // Generate a random 6-digit number and set it as the patient ID
        this.patientId = "HMS_PAT_" + String.format("%06d", (int) (Math.random() * 1000000));
    }
}
