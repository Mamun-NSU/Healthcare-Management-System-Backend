package com.healthcareapp.authenticationservice.exceptions;

public class RoleAssignException extends RuntimeException{
    private static final String MESSAGE = "Role can not be assigned!";
    public RoleAssignException(){
        super(MESSAGE);
    }
}
