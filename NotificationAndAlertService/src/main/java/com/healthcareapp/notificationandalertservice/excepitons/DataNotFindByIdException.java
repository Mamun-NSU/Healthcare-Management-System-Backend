package com.healthcareapp.notificationandalertservice.excepitons;

public class DataNotFindByIdException extends RuntimeException {
    public DataNotFindByIdException(String message) {
        super(message);
    }
}
