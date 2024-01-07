package com.healthcareapp.appointmentschedulingservice.exceptions;

public class ScheduleNotFoundException extends RuntimeException{
    public ScheduleNotFoundException(String message){
        super(message);
    }
}
