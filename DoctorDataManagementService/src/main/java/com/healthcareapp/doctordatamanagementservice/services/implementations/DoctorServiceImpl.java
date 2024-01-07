package com.healthcareapp.doctordatamanagementservice.services.implementations;
import com.healthcareapp.doctordatamanagementservice.entities.Doctor;
import com.healthcareapp.doctordatamanagementservice.entities.Room;
import com.healthcareapp.doctordatamanagementservice.enums.RoomStatus;
import com.healthcareapp.doctordatamanagementservice.excepitons.DoctorNotFoundException;
import com.healthcareapp.doctordatamanagementservice.excepitons.PasswordLengthException;
import com.healthcareapp.doctordatamanagementservice.excepitons.RoomNotFoundException;
import com.healthcareapp.doctordatamanagementservice.models.*;
import com.healthcareapp.doctordatamanagementservice.networkmanager.UserFeignClient;
import com.healthcareapp.doctordatamanagementservice.repositories.DoctorRepository;
import com.healthcareapp.doctordatamanagementservice.repositories.RoomRepository;
import com.healthcareapp.doctordatamanagementservice.services.interfaces.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.*;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final RoomRepository roomRepository;
    private final UserFeignClient userFeignClient;

    public DoctorServiceImpl(DoctorRepository doctorRepository, RoomRepository roomRepository, UserFeignClient userFeignClient) {
        this.doctorRepository = doctorRepository;
        this.roomRepository = roomRepository;
        this.userFeignClient = userFeignClient;

    }

    private String getDoctorIdFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @Override
    public void addNewDoctor(DoctorRequestDTO addDoctorDTO) {
        // Create a new Doctor entity based on the DTO and save it to the repository
        if(addDoctorDTO.getPassword().length()<5){
            throw new PasswordLengthException();
        }
        Doctor doctor = new Doctor();
        List<Room> roomList = roomRepository.findAll();
        doctor.setDfirstName(addDoctorDTO.getDfirstName());
        doctor.setDlastName(addDoctorDTO.getDlastName());
        doctor.setSpecialities(addDoctorDTO.getSpecialities());
        doctor.setQualifications(addDoctorDTO.getQualifications());
        doctor.setDoctorImage(addDoctorDTO.getDoctorImage());
        Room availableRoom = roomList.stream().filter(room -> room.getRoomStatus() == RoomStatus.Free).findFirst().orElse(null);
        RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
        registerRequestDTO.setEmail(addDoctorDTO.getEmail());
        registerRequestDTO.setPassword(addDoctorDTO.getPassword());
        registerRequestDTO.setUserId(doctor.getDoctorId());
        if (availableRoom != null) {
            doctor.setRoom(availableRoom);
            availableRoom.setRoomStatus(RoomStatus.Occupied);
            roomRepository.save(availableRoom);
        } else {
            throw new RoomNotFoundException();
        }
        doctorRepository.save(doctor);
        ResponseEntity<String> registerDoctor = userFeignClient.registerUser(registerRequestDTO);
    }

    @Override
    public void updateDoctorData(DoctorRequestDTO addDoctorDTO) {
        String doctorId = getDoctorIdFromToken();
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);

        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            if (addDoctorDTO.getSpecialities() != null && !addDoctorDTO.getSpecialities().isEmpty()) {
                doctor.setSpecialities(addDoctorDTO.getSpecialities());
            }
            if (addDoctorDTO.getQualifications() != null && !addDoctorDTO.getQualifications().isEmpty()) {
                doctor.setQualifications(addDoctorDTO.getQualifications());
            }
            doctorRepository.save(doctor);
        } else {
            throw new DoctorNotFoundException(doctorId);
        }
    }
    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
    @Override
    public DoctorResponseDTO getDoctorById(String doctorId) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            DoctorResponseDTO doctorResponseDTO = new DoctorResponseDTO();
            doctorResponseDTO.setDfirstName(doctor.getDfirstName());
            doctorResponseDTO.setDlastName(doctor.getDlastName());
            doctorResponseDTO.setSpecialities(doctor.getSpecialities());
            doctorResponseDTO.setQualifications(doctor.getQualifications());
            doctorResponseDTO.setDoctorImage(doctor.getDoctorImage());
            return doctorResponseDTO;
        } else {
            throw new DoctorNotFoundException(doctorId);
        }
    }
    public List<Doctor> getDoctorsByFiltering(DoctorFilterRequestDTO doctorFilterRequestDTO) {
        List<Doctor> allDoctors = this.doctorRepository.findAll();
        List<Doctor> filteredDoctors = new ArrayList();
        Iterator var4 = allDoctors.iterator();
        while(var4.hasNext()) {
            Doctor doctor = (Doctor)var4.next();
            boolean matchesCriteria = true;
            if (doctorFilterRequestDTO.getSpecialities() != null && !doctor.getSpecialities().contains(doctorFilterRequestDTO.getSpecialities())) {
                matchesCriteria = false;
            }
            if (doctorFilterRequestDTO.getQualifications() != null && !doctor.getQualifications().contains(doctorFilterRequestDTO.getQualifications())) {
                matchesCriteria = false;
            }
            if (matchesCriteria) {
                filteredDoctors.add(doctor);
            }
        }
        return filteredDoctors;
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