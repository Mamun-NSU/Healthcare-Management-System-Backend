package com.healthcareapp.appointmentschedulingservice.entities;

import com.healthcareapp.appointmentschedulingservice.enums.AppointmentStatus;
import com.healthcareapp.appointmentschedulingservice.enums.AppointmentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "appointment_id", updatable = false, nullable = false)
    private UUID appointmentId;

    @Column(name = "patient_id")
    @NotEmpty(message = "Patient ID cannot be null or empty")
    private String patientId;

    @Column(name = "patient_name")
    @NotEmpty(message = "Patient name can not be empty")
    private String patientName;

    @Column(name = "doctor_id")
    @NotEmpty(message = "Doctor ID cannot be null or empty")
    private String doctorId;

    @Column(name = "doctor_name")
    @NotEmpty(message = "Doctor Name can not be empty")
    private String doctorName;

    @Column(name = "appointment_date")
    @Temporal(TemporalType.DATE)
    private Date appointmentDate;

    @Column(name = "appointment_time")
    private Time appointmentTime;

    @Column(name = "problem_description")
    @NotEmpty(message = "Problem description cannot be null or empty")
    private String problemDescription;

    @Column(name = "status")
    private AppointmentStatus status;

    @Column(name = "appointment_type")
    private AppointmentType appointmentType;

    @Column(name = "serial_number")
    @NotNull(message = "Serial number cannot be null or empty")
    private Integer serialNumber;
}