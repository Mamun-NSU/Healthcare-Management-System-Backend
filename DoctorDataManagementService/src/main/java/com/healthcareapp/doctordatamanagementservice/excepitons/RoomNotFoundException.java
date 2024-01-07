package com.healthcareapp.doctordatamanagementservice.excepitons;

public class RoomNotFoundException extends RuntimeException{
    private static final String MESSAGE = "Sorry! No room is available";
    public RoomNotFoundException(){
        super(MESSAGE);
    }
}
