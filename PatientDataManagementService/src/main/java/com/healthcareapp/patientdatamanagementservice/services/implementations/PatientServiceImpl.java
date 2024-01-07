package com.healthcareapp.patientdatamanagementservice.services.implementations;
import com.healthcareapp.patientdatamanagementservice.entities.Patient;
import com.healthcareapp.patientdatamanagementservice.models.PatientRequestDTO;
import com.healthcareapp.patientdatamanagementservice.models.PatientResponseDTO;
import com.healthcareapp.patientdatamanagementservice.models.RegisterRequestDTO;
import com.healthcareapp.patientdatamanagementservice.models.UnauthorizedException;
import com.healthcareapp.patientdatamanagementservice.networkmanager.UserFeignClient;
import com.healthcareapp.patientdatamanagementservice.repositories.PatientRepository;
import com.healthcareapp.patientdatamanagementservice.services.interfaces.PatientService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final UserFeignClient userFeignClient;
    public PatientServiceImpl(PatientRepository patientRepository,
                              UserFeignClient userFeignClient){
        this.patientRepository = patientRepository;
        this.userFeignClient = userFeignClient;
    }
    private String getPatientIdFromToken(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
    @Override
    public void registerPatient(PatientRequestDTO patientRequestDTO) {
        // Map DTO to Entity
        Patient patient = new Patient();
        patient.setPhoneNo(patientRequestDTO.getPhoneNo());
        patient.setBloodGroup(patientRequestDTO.getBloodGroup());
        patient.setDateOfBirth(patientRequestDTO.getDateOfBirth());
        patient.setFirstName(patientRequestDTO.getFirstName());
        patient.setLastName(patientRequestDTO.getLastName());
        patient.setGender(patientRequestDTO.getGender());
        patient.setPatientImage(patientRequestDTO.getPatientImage());
        patient.setAge(patientRequestDTO.getAge());
        RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
        registerRequestDTO.setEmail(patientRequestDTO.getEmail());
        registerRequestDTO.setPassword(patientRequestDTO.getPassword());
        registerRequestDTO.setUserId(patient.getPatientId());

        ResponseEntity<String> register = userFeignClient.registerUser(registerRequestDTO);
        System.out.println(register);
        patientRepository.save(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public PatientResponseDTO getPatientById(String patientId) {
                String patientIdFromToken = getPatientIdFromToken();
        if(!Objects.equals(patientIdFromToken, patientId)) {
            throw new UnauthorizedException();
        }
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if(optionalPatient.isPresent()){
            PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
            BeanUtils.copyProperties(optionalPatient.get(),patientResponseDTO);
            return patientResponseDTO;
        }
        return new PatientResponseDTO();
    }

}
