package com.healthcareapp.appointmentschedulingservice.exceptions;

public class ScheduleStatusException extends RuntimeException{
    public ScheduleStatusException(String message){
        super(message);
    }
}
