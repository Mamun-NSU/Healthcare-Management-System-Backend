package com.healthcareapp.appointmentschedulingservice.controllers;

import com.healthcareapp.appointmentschedulingservice.models.AddScheduleDTO;
import com.healthcareapp.appointmentschedulingservice.models.ScheduleResponseDTO;
import com.healthcareapp.appointmentschedulingservice.models.SuccessResponse;
import com.healthcareapp.appointmentschedulingservice.services.interfaces.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;
    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }
    @PostMapping
    public ResponseEntity<SuccessResponse> scheduleAppointment(
            @RequestBody AddScheduleDTO addScheduleDTO
    ) {
        scheduleService.scheduleAppointment(addScheduleDTO);
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("Appointment scheduled successfully");
        return new ResponseEntity<>(successResponse, HttpStatus.CREATED);
    }
    @GetMapping("/get-by-id/{doctorId}")
    public ResponseEntity<ScheduleResponseDTO> getScheduleById(@PathVariable String doctorId
    ) {
        return new ResponseEntity<>(scheduleService.getDoctorSchedules(doctorId), HttpStatus.OK);
    }

    @PutMapping("/update-appointment")
    public ResponseEntity<String> updateAppointment(
            @RequestBody AddScheduleDTO addScheduleDTO
    ) {
        scheduleService.updateAppointment(addScheduleDTO);
        return new ResponseEntity<>("Appointment updated successfully", HttpStatus.OK);
    }
    @DeleteMapping("/cancel/{doctorId}")
    public ResponseEntity<SuccessResponse> cancelSchedule(@PathVariable String doctorId) {
        scheduleService.cancelSchedule(doctorId);
        SuccessResponse successResponse = new SuccessResponse("Schedule canceled successfully");
        return ResponseEntity.status(HttpStatus.OK).body(successResponse);
    }
    @PutMapping("/change-over-status/{doctorId}")
    public ResponseEntity<SuccessResponse> changeScheduleOverStatus(@PathVariable String doctorId) {
        scheduleService.changeScheduleOverStatus(doctorId);
        SuccessResponse successResponse = new SuccessResponse("Schedule status changed to 'Over' successfully.");
        return ResponseEntity.ok(successResponse);
    }
    @PutMapping("/change-ongoing-status/{doctorId}")
    public ResponseEntity<SuccessResponse> changeOngoingScheduleStatus(@PathVariable String doctorId) {
        scheduleService.changeOngoingScheduleStatus(doctorId);
        SuccessResponse successResponse = new SuccessResponse("Schedule status changed to 'Ongoing' successfully.");
        return ResponseEntity.ok(successResponse);
    }
}
