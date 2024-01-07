package com.healthcareapp.pharmaceuticalinventorymanagementservice.exceptions;

public class DataNotFindByIdException extends RuntimeException {
    public DataNotFindByIdException(String message) {
        super(message);
    }
}
