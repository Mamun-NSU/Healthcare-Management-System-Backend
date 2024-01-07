package com.healthcareapp.patientdatamanagementservice.services.implementations;


import com.healthcareapp.patientdatamanagementservice.entities.HealthData;
import com.healthcareapp.patientdatamanagementservice.entities.Patient;
import com.healthcareapp.patientdatamanagementservice.exceptions.PatientHealthDataNotFoundException;
import com.healthcareapp.patientdatamanagementservice.exceptions.PatientNotFoundException;
import com.healthcareapp.patientdatamanagementservice.models.HealthRequestDTO;
import com.healthcareapp.patientdatamanagementservice.repositories.HealthDataRepository;
import com.healthcareapp.patientdatamanagementservice.repositories.PatientRepository;
import com.healthcareapp.patientdatamanagementservice.services.interfaces.HealthDataService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class HealthDataServiceImpl implements HealthDataService {

    private final HealthDataRepository healthDataRepository;
    private final PatientRepository patientRepository;

    public HealthDataServiceImpl(HealthDataRepository healthDataRepository,
                                 PatientRepository patientRepository) {
        this.healthDataRepository = healthDataRepository;
        this.patientRepository = patientRepository;
    }
    private String getPatientIdFromToken(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }


    @Override
    public void addHealthData( HealthRequestDTO healthRequestDTO) {
        String patientId = getPatientIdFromToken();
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if (optionalPatient.isPresent()) {
            HealthData healthData = getData(healthRequestDTO, optionalPatient);
            healthDataRepository.save(healthData);
        } else {
            throw new PatientNotFoundException(patientId);
        }
    }

    private HealthData getData(HealthRequestDTO healthRequestDTO, Optional<Patient> optionalPatient) {
        Patient patient = optionalPatient.get();

        // Create a new HealthData entity
        HealthData healthData = new HealthData();
        healthData.setPatient(patient);
        healthData.setHeight(healthRequestDTO.getHeight());
        healthData.setWeight(healthRequestDTO.getWeight());
        healthData.setHighBloodPressure(healthRequestDTO.getHighBloodPressure());
        healthData.setLowBloodPressure(healthRequestDTO.getLowBloodPressure());
        healthData.setHeartRate(healthRequestDTO.getHeartRate());
        healthData.setSugarLevel(healthRequestDTO.getSugarLevel());
        healthData.setDiabetesStatus(healthRequestDTO.getDiabetesStatus());
        healthData.setAllergie(healthRequestDTO.getAllergie());
        return healthData;
    }

    @Override
    public HealthData findHealthDataById() {
        String patientId = getPatientIdFromToken();
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if(optionalPatient.isPresent()){
            Optional<HealthData> optionalHealthData = healthDataRepository.findByPatient(optionalPatient.get());
            return optionalHealthData.get();
        }else {
            throw new PatientHealthDataNotFoundException("Patient Health Data not found with ID: " + patientId);
        }

    }
    @Override
    public void updateHealthData(HealthRequestDTO healthRequestDTO) {
        // Retrieve the health data by patient ID
        String patientId = getPatientIdFromToken();
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if(optionalPatient.isPresent()) {
            Optional<HealthData> optionalHealthData = healthDataRepository.findByPatient(optionalPatient.get());
            if (optionalHealthData.isPresent()) {
                HealthData existingHealthData = optionalHealthData.get();

                // Update the fields if they are not null in the DTO
                if (healthRequestDTO.getHeight() != null) {
                    existingHealthData.setHeight(healthRequestDTO.getHeight());
                }
                if (healthRequestDTO.getWeight() != null) {
                    existingHealthData.setWeight(healthRequestDTO.getWeight());
                }
                if (healthRequestDTO.getHighBloodPressure() != null) {
                    existingHealthData.setHighBloodPressure(healthRequestDTO.getHighBloodPressure());
                }
                if (healthRequestDTO.getLowBloodPressure() != null) {
                    existingHealthData.setLowBloodPressure(healthRequestDTO.getLowBloodPressure());
                }
                if (healthRequestDTO.getHeartRate() != null) {
                    existingHealthData.setHeartRate(healthRequestDTO.getHeartRate());
                }
                if (healthRequestDTO.getSugarLevel() != null) {
                    existingHealthData.setSugarLevel(healthRequestDTO.getSugarLevel());
                }
                if (healthRequestDTO.getDiabetesStatus() != null) {
                    existingHealthData.setDiabetesStatus(healthRequestDTO.getDiabetesStatus());
                }
                if (healthRequestDTO.getAllergie() != null) {
                    existingHealthData.setAllergie(healthRequestDTO.getAllergie());
                }

                // Repeat this pattern for other fields...

                healthDataRepository.save(existingHealthData);

            } else {
                throw new PatientHealthDataNotFoundException("Patient Health Data not found with ID: " + patientId);
            }
        }
    }

    private HealthData getHealthData(HealthRequestDTO healthRequestDTO, Optional<HealthData> optionalHealthData) {
        HealthData healthData = optionalHealthData.get();

        // Update the health data fields
        healthData.setHeight(healthRequestDTO.getHeight());
        healthData.setWeight(healthRequestDTO.getWeight());
        healthData.setHighBloodPressure(healthRequestDTO.getHighBloodPressure());
        healthData.setLowBloodPressure(healthRequestDTO.getLowBloodPressure());
        healthData.setHeartRate(healthRequestDTO.getHeartRate());
        healthData.setSugarLevel(healthRequestDTO.getSugarLevel());
        healthData.setDiabetesStatus(healthRequestDTO.getDiabetesStatus());
        healthData.setAllergie(healthRequestDTO.getAllergie());
        return healthData;
    }

}

