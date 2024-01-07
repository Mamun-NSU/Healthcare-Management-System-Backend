package com.healthcareapp.patientdatamanagementservice.exceptions;

public class PatientNotFoundException extends RuntimeException{
    private static final String MESSAGE = "Patient not found with ID: ";
    public PatientNotFoundException(String patientId){
        super(MESSAGE+patientId);
    }
}
