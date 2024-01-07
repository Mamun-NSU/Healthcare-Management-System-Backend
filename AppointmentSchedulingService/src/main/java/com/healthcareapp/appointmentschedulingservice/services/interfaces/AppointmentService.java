package com.healthcareapp.appointmentschedulingservice.services.interfaces;

import com.healthcareapp.appointmentschedulingservice.entities.Appointments;
import com.healthcareapp.appointmentschedulingservice.models.AddAppointmentDTO;

import java.util.List;

public interface AppointmentService {
    void addAppointment(AddAppointmentDTO addAppointmentDTO);
    Appointments getUpcomingAppointmentsForPatient();
    List<Appointments> getUpcomingAppointmentsForDoctor(String doctorId);
    void cancelAppointment(String patientId);
    void updateAppointmentStatus(String doctorId);
}

