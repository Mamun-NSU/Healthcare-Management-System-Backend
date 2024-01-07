package com.healthcareapp.appointmentschedulingservice.repositories;

import com.healthcareapp.appointmentschedulingservice.entities.Schedule;
import com.healthcareapp.appointmentschedulingservice.enums.ScheduleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
    List<Schedule> findByDoctorId(String doctorId);
    Optional<Schedule> findByDoctorIdAndStatus(String doctorId, ScheduleStatus scheduleStatus);
}
