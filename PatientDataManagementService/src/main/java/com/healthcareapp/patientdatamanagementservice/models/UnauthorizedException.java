package com.healthcareapp.patientdatamanagementservice.models;

public class UnauthorizedException extends RuntimeException{
    private static final String MESSAGE = "Sorry! You are not authorized";
    public UnauthorizedException(){
        super(MESSAGE);
    }
}
