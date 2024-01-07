package com.healthcareapp.doctordatamanagementservice.excepitons;

public class PasswordLengthException extends RuntimeException{
    private final static String MESSAGE = "Password must be 5 characters long";
    public PasswordLengthException(){
        super(MESSAGE);
    }
}
