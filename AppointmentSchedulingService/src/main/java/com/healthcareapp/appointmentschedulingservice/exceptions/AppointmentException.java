package com.healthcareapp.appointmentschedulingservice.exceptions;

public class AppointmentException extends RuntimeException{
    public AppointmentException(String message){
        super(message);
    }
}
