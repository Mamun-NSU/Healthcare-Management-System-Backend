package com.healthcareapp.doctordatamanagementservice.entities;

import com.healthcareapp.doctordatamanagementservice.enums.Gender;
import com.healthcareapp.doctordatamanagementservice.enums.Qualifications;
import com.healthcareapp.doctordatamanagementservice.enums.Specialities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "doctor")
public class Doctor {
    @Id
    @Column(name = "doctor_id", updatable = false, nullable = false)
    private String doctorId;
    @Column(name = "first_name")
    private String dfirstName;

    @Column(name = "last_name")
    private String dlastName;

    @Column(name = "image_path")
    private String doctorImage;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender dgender;

    @Enumerated(EnumType.STRING)
    @Column(name = "specialities")
    private Set<Specialities> specialities;

    @Enumerated(EnumType.STRING)
    @Column(name = "qualifications")
    private Set<Qualifications> qualifications;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    public Doctor() {
        this.doctorId = "HMS_DOC_" + String.format("%04d", (int) (Math.random() * 10000));
    }
}
