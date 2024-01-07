package com.healthcareapp.patientdatamanagementservice.exceptions;

public class PatientHealthDataNotFoundException extends RuntimeException{
    private static final String MESSAGE = "Patient Health not found with ID: ";
    public PatientHealthDataNotFoundException(String patientId){
        super(MESSAGE+patientId);
    }
}