package com.healthcareapp.patientdatamanagementservice.entities;

import com.healthcareapp.patientdatamanagementservice.enums.Allergie;
import com.healthcareapp.patientdatamanagementservice.enums.DiabetesStatus;
import com.healthcareapp.patientdatamanagementservice.enums.SugarLevel;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "health_data")
@Getter
@Setter
@RequiredArgsConstructor
public class HealthData {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "health_data_id")
    private UUID id;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name = "height")
    private double height;

    @Column(name = "weight")
    private double weight;

    @Column(name = "high_blood_pressure")
    private double highBloodPressure;

    @Column(name = "low_blood_pressure")
    private double lowBloodPressure;

    @Column(name = "heart_rate")
    private double heartRate;

    @Column(name = "diabetesStatus")
    @Enumerated(EnumType.STRING)
    private DiabetesStatus diabetesStatus;

    @Column(name = "allergie")
    private Allergie allergie;

    @Column(name = "sugar_level")
    @Enumerated(EnumType.STRING)
    private SugarLevel sugarLevel;
}
