package com.healthcareapp.doctordatamanagementservice.excepitons;

public class DoctorNotFoundException extends RuntimeException{
    private static final String MESSAGE = "Doctor not found with ID: ";
    public DoctorNotFoundException(String patientId){
        super(MESSAGE+patientId);
    }
}
