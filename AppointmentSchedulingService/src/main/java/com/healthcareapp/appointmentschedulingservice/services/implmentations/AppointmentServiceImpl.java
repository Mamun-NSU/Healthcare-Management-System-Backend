package com.healthcareapp.appointmentschedulingservice.services.implmentations;

import com.healthcareapp.appointmentschedulingservice.entities.Appointments;
import com.healthcareapp.appointmentschedulingservice.entities.Schedule;
import com.healthcareapp.appointmentschedulingservice.enums.AppointmentStatus;
import com.healthcareapp.appointmentschedulingservice.enums.NotificationType;
import com.healthcareapp.appointmentschedulingservice.enums.ScheduleStatus;
import com.healthcareapp.appointmentschedulingservice.exceptions.AppointmentException;
import com.healthcareapp.appointmentschedulingservice.exceptions.InvalidRequestException;
import com.healthcareapp.appointmentschedulingservice.exceptions.ScheduleNotFoundException;
import com.healthcareapp.appointmentschedulingservice.exceptions.UnauthorizedException;
import com.healthcareapp.appointmentschedulingservice.models.*;
import com.healthcareapp.appointmentschedulingservice.networkmanager.NotificationFeignClient;
import com.healthcareapp.appointmentschedulingservice.networkmanager.PatientFeignClient;
import com.healthcareapp.appointmentschedulingservice.repositories.AppointmentRepository;
import com.healthcareapp.appointmentschedulingservice.repositories.ScheduleRepository;
import com.healthcareapp.appointmentschedulingservice.services.interfaces.AppointmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ScheduleRepository scheduleRepository;
    private final PatientFeignClient patientFeignClient;

    private final NotificationFeignClient notificationFeignClient;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  ScheduleRepository scheduleRepository,
                                  PatientFeignClient patientFeignClient,
                                  NotificationFeignClient notificationFeignClient) {
        this.appointmentRepository = appointmentRepository;
        this.patientFeignClient = patientFeignClient;
        this.scheduleRepository = scheduleRepository;
        this.notificationFeignClient = notificationFeignClient;
    }

    private String getIdFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @Override
    public void addAppointment(AddAppointmentDTO addAppointmentDTO) {
        String patientId = getIdFromToken();
        Optional<Appointments> upcomingAppointments = appointmentRepository
                .findByPatientIdAndStatus(patientId, AppointmentStatus.UPCOMING);
        if (upcomingAppointments.isPresent()) {
            throw new AppointmentException("Sorry! You have already booked an appointment!");
        }
        // Check if the specified serial number is already booked
        int serialNumber = addAppointmentDTO.getSerialNumber();
        boolean isSerialNumberBooked = appointmentRepository.existsByDoctorIdAndSerialNumberAndStatus(
                addAppointmentDTO.getDoctorId(), serialNumber, AppointmentStatus.UPCOMING);

        if (isSerialNumberBooked) {
            throw new AppointmentException("Sorry! The specified serial number is already booked.");
        }
        ResponseEntity<PatientResponseDTO> response = patientFeignClient.findPatientById(patientId);
        Optional<Schedule> optionalSchedule = scheduleRepository
                .findByDoctorIdAndStatus(addAppointmentDTO.getDoctorId(), ScheduleStatus.UPCOMING);
        Appointments newAppointment = new Appointments();
        LocalDateTime appointmentDateTime = null;
        Time appointmentTime = null;
        if (optionalSchedule.isPresent()) {
            Schedule schedule = optionalSchedule.get();
            Time startTime = schedule.getStartTime();
            int timePerPatient = schedule.getTimePerPatient();
            int minutesToAdd = (serialNumber - 1) * timePerPatient;
            appointmentDateTime = startTime.toLocalTime().atDate(LocalDate.now()).plusMinutes(minutesToAdd);
            appointmentTime = Time.valueOf(appointmentDateTime.toLocalTime());
            newAppointment.setAppointmentDate(schedule.getAvailableDay());
            newAppointment.setAppointmentTime(appointmentTime);
        } else {
            throw new AppointmentException("Sorry! the doctor has no upcoming schedule for an appointment");
        }
        PatientResponseDTO responseDTO = response.getBody();
        BeanUtils.copyProperties(addAppointmentDTO, newAppointment);
        newAppointment.setStatus(AppointmentStatus.UPCOMING);
        newAppointment.setPatientId(patientId);
        assert responseDTO != null;
        newAppointment.setPatientName(responseDTO.getFirstName() + " " + responseDTO.getLastName());
        appointmentRepository.save(newAppointment);

        AddingNotificationDTO notificationDTO = new AddingNotificationDTO();
        notificationDTO.setUserId(patientId);
        notificationDTO.setNotificationType(NotificationType.APPOINTMENT_REMINDER);
        notificationDTO.setMessage("You have an upcoming appointment on " + optionalSchedule.get().getAvailableDay()
                + " at " + appointmentTime + " with " + addAppointmentDTO.getDoctorName());
        ResponseEntity<SuccessResponse> createNotification = notificationFeignClient.createNewNotification(notificationDTO);
    }

    @Override
    public Appointments getUpcomingAppointmentsForPatient() {
        String patientId = getIdFromToken();
        Optional<Appointments> optionalAppointments = appointmentRepository
                .findByPatientIdAndStatus(patientId, AppointmentStatus.UPCOMING);
        return optionalAppointments.orElseGet(Appointments::new);
    }

    @Override
    public List<Appointments> getUpcomingAppointmentsForDoctor(String doctorId) {
        return appointmentRepository.findByDoctorIdAndStatus(doctorId, AppointmentStatus.UPCOMING);
    }

    @Override
    public void cancelAppointment(String patientId) {
        String patientIdFromToken = getIdFromToken();
        if (!Objects.equals(patientId, patientIdFromToken)) {
            throw new UnauthorizedException();
        }
        Optional<Appointments> upcomingAppointments = appointmentRepository.findByPatientIdAndStatus(
                patientId, AppointmentStatus.UPCOMING);

        if (upcomingAppointments.isEmpty()) {
            throw new InvalidRequestException("No upcoming appointments found for the patient.");
        }

        Appointments appointment = upcomingAppointments.get();
        appointmentRepository.delete(appointment);
    }

    @Override
    public void updateAppointmentStatus(String doctorId) {
        String idFromToken = getIdFromToken();
        if(!Objects.equals(doctorId, idFromToken)){
            throw new UnauthorizedException();
        }
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<Appointments> appointmentsList = appointmentRepository
                .findByDoctorIdAndStatus(doctorId, AppointmentStatus.UPCOMING);
        appointmentsList.forEach(appointment -> appointment.setStatus(AppointmentStatus.HAPPENED));
        appointmentRepository.saveAll(appointmentsList);
        if (appointmentsList.isEmpty()) {
            throw new ScheduleNotFoundException("No upcoming appointments found for the specified doctor.");
        }
    }


}

