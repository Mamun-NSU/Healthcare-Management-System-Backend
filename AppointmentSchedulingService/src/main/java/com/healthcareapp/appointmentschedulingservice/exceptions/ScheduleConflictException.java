package com.healthcareapp.appointmentschedulingservice.exceptions;

public class ScheduleConflictException extends RuntimeException{
    public ScheduleConflictException(String message){
        super(message);
    }
}
