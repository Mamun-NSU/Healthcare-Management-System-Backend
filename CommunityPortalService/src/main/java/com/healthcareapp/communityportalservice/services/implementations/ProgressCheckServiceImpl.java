package com.healthcareapp.communityportalservice.services.implementations;

import com.healthcareapp.communityportalservice.entities.ProgressCheck;
import com.healthcareapp.communityportalservice.exceptions.UnauthorizedException;
import com.healthcareapp.communityportalservice.repositories.ProgressCheckRepository;
import com.healthcareapp.communityportalservice.services.interfaces.ProgressCheckService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProgressCheckServiceImpl implements ProgressCheckService {
    private final ProgressCheckRepository progressCheckRepository;
    public ProgressCheckServiceImpl(ProgressCheckRepository progressCheckRepository){
        this.progressCheckRepository = progressCheckRepository;
    }
    private String getPatientIdFromToken(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @Override
    public void insertOrUpdateProgressCheck(String patientId, ProgressCheck progressCheck) {
        if(!Objects.equals(patientId, getPatientIdFromToken())){
            throw new UnauthorizedException();
        }
        if (progressCheck.getGoalWeight()!=null && progressCheck.getCurrentWeight()!=null &&(progressCheck.getCurrentWeight() < 0 || progressCheck.getCurrentWeight()>500
                || progressCheck.getGoalWeight()<0 || progressCheck.getGoalWeight()>500)){
            throw new IllegalArgumentException("The given weight or goal weight is not valid");
        }
        ProgressCheck existingProgressCheck = progressCheckRepository.findByPatientId(patientId);

        if (existingProgressCheck != null) {
            // If progress check already exists for the patient, update it
            if(progressCheck.getCurrentWeight()!=null){
                existingProgressCheck.setCurrentWeight(progressCheck.getCurrentWeight());
            }
            else{
                existingProgressCheck.setGoalWeight(progressCheck.getGoalWeight());
            }
            // Update other fields as needed
            progressCheckRepository.save(existingProgressCheck);
        } else{
            // If no progress check exists for the patient, insert a new one
            progressCheck.setPatientId(patientId);
            progressCheckRepository.save(progressCheck);
        }
    }

    @Override
    public ProgressCheck getProgressById() {
        String patientId = getPatientIdFromToken();
        return progressCheckRepository.findByPatientId(patientId);
    }

    @Override
    public String checkProgress(String patientId) {
        if(!Objects.equals(patientId, getPatientIdFromToken())){
            throw new UnauthorizedException();
        }
        ProgressCheck latestProgressCheck = progressCheckRepository.findByPatientId(patientId);

        if (latestProgressCheck == null) {
            return "No progress check found for the patient.";
        }

        double difference = latestProgressCheck.getCurrentWeight() - latestProgressCheck.getGoalWeight();

        if (difference > 0) {
            return "You are " + difference + " kg more than your goal weight.";
        } else if (difference < 0) {
            return "You are " + Math.abs(difference) + " kg less than your goal weight.";
        } else {
            return "Congratulations! You have reached your goal weight.";
        }
    }
}

