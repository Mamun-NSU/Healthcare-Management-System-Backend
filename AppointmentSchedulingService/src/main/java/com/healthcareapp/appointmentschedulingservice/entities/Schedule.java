package com.healthcareapp.appointmentschedulingservice.entities;
import com.healthcareapp.appointmentschedulingservice.enums.AppointmentType;
import com.healthcareapp.appointmentschedulingservice.enums.ScheduleStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import java.sql.Time;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Schedule")
public class Schedule {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "schedule_id", columnDefinition = "BINARY(16)")
    private UUID scheduleId;

    @Column(name = "doctor_id")
    private String doctorId;

    @Column(name = "available_day")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Available day cannot be null or empty")
    private Date availableDay;

    @Column(name = "start_time")
    @NotNull(message = "Start time cannot be null or empty")
    private Time startTime;

    @Column(name = "time_per_patient")
    @NotNull(message = "Time per patient cannot be null or empty")
    private Integer timePerPatient;

    @Column(name = "patient_numbers")
    @NotNull(message = "Patient numbers cannot be null or empty")
    private Integer patientNumbers;

    @Column(name = "end_time")
    private Time endTime;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ScheduleStatus status;

    @Column(name = "appointment_type")
    @Enumerated(EnumType.STRING)
    private AppointmentType appointmentType;
}

