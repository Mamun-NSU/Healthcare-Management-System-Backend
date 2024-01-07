package com.healthcareapp.communityportalservice.exceptions;

public class UnauthorizedException extends RuntimeException{
    private static final String MESSAGE = "Sorry! You are not authorized";
    public UnauthorizedException(){
        super(MESSAGE);
    }
}
