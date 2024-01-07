package com.healthcareapp.clinicaldecisionsupportsystemservice.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Report")
public class Report {
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false)
    private UUID id;

    @Column(name = "patient_id")
    @NotEmpty(message = "Patient id can not be null or empty")
    private String patientId;

    @Column(name = "diagnoses")
    @NotEmpty(message = "Diagnoses can not be null or empty")
    private String diagnoses;

    @Column(name = "treatment_plan")
    @NotEmpty(message = "Treatment plan can not be null or empty")
    private String treatmentPlan;

    @Column(name = "medication")
    @NotEmpty(message = "Medication can not be null or empty")
    private String medication;

    @Column(name = "generated_time")
    private LocalDateTime generatedTime;
}
