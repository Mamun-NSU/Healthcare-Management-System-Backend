package com.healthcareapp.appointmentschedulingservice.repositories;

import com.healthcareapp.appointmentschedulingservice.entities.Appointments;
import com.healthcareapp.appointmentschedulingservice.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointments, UUID> {
    Optional<Appointments> findByPatientIdAndStatus(String patientId, AppointmentStatus status);
    List<Appointments> findByDoctorIdAndStatus(String doctorId, AppointmentStatus status);
    Boolean existsByDoctorIdAndSerialNumberAndStatus(String doctorId, Integer serialNumber, AppointmentStatus status);
    Boolean existsByDoctorIdAndStatus(String doctorId, AppointmentStatus status);
}

