package com.healthcareapp.appointmentschedulingservice.controllers;
import com.healthcareapp.appointmentschedulingservice.entities.Appointments;
import com.healthcareapp.appointmentschedulingservice.models.AddAppointmentDTO;
import com.healthcareapp.appointmentschedulingservice.models.SuccessResponse;
import com.healthcareapp.appointmentschedulingservice.services.interfaces.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/add")
    public ResponseEntity<SuccessResponse> addAppointment(@RequestBody AddAppointmentDTO addAppointmentDTO) {
        appointmentService.addAppointment(addAppointmentDTO);
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("Appointment added successfully");
        return new ResponseEntity<>(successResponse, HttpStatus.CREATED);
    }

    @GetMapping("/patient")
    public ResponseEntity<Appointments> getUpcomingAppointmentsForPatient() {
        Appointments upcomingAppointment = appointmentService.getUpcomingAppointmentsForPatient();
        return new ResponseEntity<>(upcomingAppointment, HttpStatus.OK);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Appointments>> getUpcomingAppointmentsForDoctor(@PathVariable String doctorId) {
        List<Appointments> upcomingAppointments = appointmentService
                .getUpcomingAppointmentsForDoctor(doctorId);
        return new ResponseEntity<>(upcomingAppointments, HttpStatus.OK);
    }
    @DeleteMapping("/cancel-appointment")
    public ResponseEntity<SuccessResponse> cancelAppointment(@RequestParam String patientId) {
        appointmentService.cancelAppointment(patientId);
        SuccessResponse successResponse = new SuccessResponse("Appointment canceled successfully");
        return ResponseEntity.ok(successResponse);
    }
    @PutMapping("/update-status/{doctorId}")
    public ResponseEntity<SuccessResponse> changeAppointmentStatus(@PathVariable String doctorId) {
        appointmentService.updateAppointmentStatus(doctorId);
        SuccessResponse successResponse = new SuccessResponse("Appointments status changed successfully.");
        return ResponseEntity.ok(successResponse);
    }
}

