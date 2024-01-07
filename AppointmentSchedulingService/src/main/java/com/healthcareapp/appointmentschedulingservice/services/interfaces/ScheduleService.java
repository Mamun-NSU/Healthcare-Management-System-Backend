package com.healthcareapp.appointmentschedulingservice.services.interfaces;

import com.healthcareapp.appointmentschedulingservice.models.AddScheduleDTO;
import com.healthcareapp.appointmentschedulingservice.models.ScheduleResponseDTO;


public interface ScheduleService {
    void scheduleAppointment(AddScheduleDTO addScheduleDTO);
    ScheduleResponseDTO getDoctorSchedules(String doctorId);
    void updateAppointment(AddScheduleDTO addScheduleDTO);
    void cancelSchedule(String doctorId);

    void changeScheduleOverStatus(String doctorId);
    void changeOngoingScheduleStatus(String doctorId);
}
