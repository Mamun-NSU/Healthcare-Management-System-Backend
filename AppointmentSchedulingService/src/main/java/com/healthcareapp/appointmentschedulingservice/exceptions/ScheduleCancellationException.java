package com.healthcareapp.appointmentschedulingservice.exceptions;

public class ScheduleCancellationException extends RuntimeException{
    public ScheduleCancellationException(String message){
        super(message);
    }
}
