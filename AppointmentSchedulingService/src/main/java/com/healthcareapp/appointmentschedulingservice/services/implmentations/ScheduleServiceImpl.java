package com.healthcareapp.appointmentschedulingservice.services.implmentations;

import com.healthcareapp.appointmentschedulingservice.entities.Appointments;
import com.healthcareapp.appointmentschedulingservice.entities.Schedule;
import com.healthcareapp.appointmentschedulingservice.enums.AppointmentStatus;
import com.healthcareapp.appointmentschedulingservice.enums.ScheduleStatus;
import com.healthcareapp.appointmentschedulingservice.exceptions.*;
import com.healthcareapp.appointmentschedulingservice.models.AddScheduleDTO;
import com.healthcareapp.appointmentschedulingservice.models.DoctorResponseDTO;
import com.healthcareapp.appointmentschedulingservice.models.ScheduleResponseDTO;
import com.healthcareapp.appointmentschedulingservice.networkmanager.DoctorFeignClient;
import com.healthcareapp.appointmentschedulingservice.repositories.AppointmentRepository;
import com.healthcareapp.appointmentschedulingservice.repositories.ScheduleRepository;
import com.healthcareapp.appointmentschedulingservice.services.interfaces.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorFeignClient doctorFeignClient;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository,
                               DoctorFeignClient doctorFeignClient,
                               AppointmentRepository appointmentRepository) {
        this.scheduleRepository = scheduleRepository;
        this.doctorFeignClient = doctorFeignClient;
        this.appointmentRepository = appointmentRepository;
    }

    private String getDoctorIdFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @Override
    public void scheduleAppointment(AddScheduleDTO addScheduleDTO) {
        String doctorId = getDoctorIdFromToken();

        // Check if the doctor has an upcoming schedule
        Optional<Schedule> existingSchedule = scheduleRepository.findByDoctorIdAndStatus(doctorId, ScheduleStatus.UPCOMING);

        if (existingSchedule.isPresent()) {
            throw new ScheduleConflictException("Doctor already has an upcoming schedule");
        }

        // Proceed with scheduling the new appointment
        Schedule schedule = new Schedule();
        schedule.setDoctorId(doctorId);
        schedule.setAvailableDay(addScheduleDTO.getAvailableDay());
        schedule.setStartTime(addScheduleDTO.getStartTime());
        schedule.setTimePerPatient(addScheduleDTO.getTimePerPatient());
        schedule.setPatientNumbers(addScheduleDTO.getPatientNumbers());
        schedule.setEndTime(calculateEndTime(
                addScheduleDTO.getStartTime(),
                addScheduleDTO.getPatientNumbers(),
                addScheduleDTO.getTimePerPatient()
        ));
        schedule.setStatus(ScheduleStatus.UPCOMING);
        schedule.setAppointmentType(addScheduleDTO.getAppointmentType());
        scheduleRepository.save(schedule);
    }

    @Override
    public ScheduleResponseDTO getDoctorSchedules(String doctorId) {
        ResponseEntity<DoctorResponseDTO> response = doctorFeignClient.findDoctorById(doctorId);
        DoctorResponseDTO doctorResponseDTO = response.getBody();
        Optional<Schedule> optionalSchedule = scheduleRepository.findByDoctorIdAndStatus(doctorId, ScheduleStatus.UPCOMING);
        if (optionalSchedule.isPresent()) {
            Schedule schedule = optionalSchedule.get();
            ScheduleResponseDTO scheduleResponseDTO = new ScheduleResponseDTO();
            assert doctorResponseDTO != null;
            scheduleResponseDTO.setDfirstname(doctorResponseDTO.getDfirstName());
            scheduleResponseDTO.setDlastName(doctorResponseDTO.getDlastName());
            scheduleResponseDTO.setAvailableDay(schedule.getAvailableDay());
            scheduleResponseDTO.setStartTime(schedule.getStartTime());
            scheduleResponseDTO.setTimePerPatient(schedule.getTimePerPatient());
            scheduleResponseDTO.setPatientNumbers(schedule.getPatientNumbers());
            scheduleResponseDTO.setAppointmentType(schedule.getAppointmentType());
            return scheduleResponseDTO;
        } else {
            throw new ScheduleNotFoundException("No appointment schedule found for this doctor");
        }
    }

    @Override
    public void updateAppointment(AddScheduleDTO addScheduleDTO) {
        String doctorId = getDoctorIdFromToken();
        List<Appointments> upcomingAppointments = appointmentRepository.findByDoctorIdAndStatus(
                doctorId, AppointmentStatus.UPCOMING);
        if (!upcomingAppointments.isEmpty()) {
            throw new ScheduleCancellationException("Doctor has upcoming appointments. Cannot cancel schedule.");
        }
        Optional<Schedule> existingSchedule = scheduleRepository
                .findByDoctorIdAndStatus(doctorId, ScheduleStatus.UPCOMING);
        if (existingSchedule.isPresent()) {
            Schedule schedule = existingSchedule.get();
            Time startTime = schedule.getStartTime();
            int patientNumbers = schedule.getPatientNumbers();
            int timePerPatient = schedule.getTimePerPatient();
            if (addScheduleDTO.getAvailableDay() != null) {
                schedule.setAvailableDay(addScheduleDTO.getAvailableDay());
            }
            if (addScheduleDTO.getStartTime() != null) {
                startTime = addScheduleDTO.getStartTime();
                schedule.setStartTime(addScheduleDTO.getStartTime());
            }
            if (addScheduleDTO.getTimePerPatient() != null) {
                timePerPatient = addScheduleDTO.getTimePerPatient();
                schedule.setTimePerPatient(addScheduleDTO.getTimePerPatient());
            }
            if (addScheduleDTO.getPatientNumbers() != null) {
                patientNumbers = addScheduleDTO.getPatientNumbers();
                schedule.setPatientNumbers(addScheduleDTO.getPatientNumbers());
            }
            System.out.println(patientNumbers);
            schedule.setEndTime(calculateEndTime(startTime, patientNumbers, timePerPatient));

            scheduleRepository.save(schedule);
        } else {
            throw new ScheduleNotFoundException("No upcoming schedule found for the doctor.");
        }
    }

    @Override
    public void cancelSchedule(String doctorId) {
        String doctorIdFromToken = getDoctorIdFromToken();
        if (!doctorIdFromToken.equals(doctorId)) {
            throw new UnauthorizedException();
        }
        List<Appointments> upcomingAppointments = appointmentRepository.findByDoctorIdAndStatus(
                doctorId, AppointmentStatus.UPCOMING);

        if (!upcomingAppointments.isEmpty()) {
            throw new ScheduleCancellationException("Doctor has upcoming appointments. Cannot cancel schedule.");
        }
        Optional<Schedule> optionalSchedule = scheduleRepository.findByDoctorIdAndStatus(doctorId, ScheduleStatus.UPCOMING);

        if (optionalSchedule.isPresent()) {
            Schedule schedule = optionalSchedule.get();
            scheduleRepository.delete(schedule);
        } else {

            throw new ScheduleNotFoundException("No upcoming schedule found for the doctor.");
        }
    }

    @Override
    public void changeScheduleOverStatus(String doctorId) {
        if (!Objects.equals(getDoctorIdFromToken(), doctorId)) {
            throw new UnauthorizedException();
        }
        boolean hasUpcomingAppointments = appointmentRepository.existsByDoctorIdAndStatus(doctorId, AppointmentStatus.UPCOMING);

        if (hasUpcomingAppointments) {
            throw new ScheduleStatusException("Cannot change schedule status to 'Over' as there are upcoming appointments.");
        }
        Optional<Schedule> optionalSchedule = scheduleRepository.findByDoctorIdAndStatus(doctorId, ScheduleStatus.ONGOING);
        optionalSchedule.ifPresent(schedule -> {
            schedule.setStatus(ScheduleStatus.OVER);
            scheduleRepository.save(schedule);
        });
    }

    @Override
    public void changeOngoingScheduleStatus(String doctorId) {
        if (!Objects.equals(getDoctorIdFromToken(), doctorId)) {
            throw new UnauthorizedException();
        }
        Optional<Schedule> optionalSchedule = scheduleRepository
                .findByDoctorIdAndStatus(doctorId, ScheduleStatus.UPCOMING);

        if (optionalSchedule.isPresent()) {
            Schedule schedule = optionalSchedule.get();
            schedule.setStatus(ScheduleStatus.ONGOING);
            scheduleRepository.save(schedule);
        } else {
            throw new ScheduleStatusException("Cannot change schedule status to 'Ongoing' as the scheduled time has not been reached.");
        }
    }


    private Time calculateEndTime(Time startTime, int patientNumbers, int timePerPatient) {
        int totalDuration = timePerPatient * patientNumbers;
        LocalTime startTimeLocalTime = startTime.toLocalTime();
        int startHour = startTimeLocalTime.getHour();
        int startMinute = startTimeLocalTime.getMinute();
        int endHour = (startHour + (totalDuration / 60)) % 24;
        int endMinute = (startMinute + (totalDuration % 60)) % 60;
        return Time.valueOf(String.format("%02d:%02d:00", endHour, endMinute));
    }
}
