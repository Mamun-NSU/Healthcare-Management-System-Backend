package com.healthcareapp.communityportalservice.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "progress_checks")
public class ProgressCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "progress_check_id", updatable = false)
    private UUID progressCheckId;

    @Column(name = "patient_id")
    private String patientId;

    @Column(name = "current_weight")
    @NotNull(message = "Current weight cannot be null or empty")
    private Double currentWeight;

    @Column(name = "goal_weight")
    @NotNull(message = "Goal weight cannot be null or empty")
    private Double goalWeight;

}

