package com.healthcareapp.patientdatamanagementservice.constants;

public class AppConstants {
    public static final String TOKEN_SECRET = "SM2P_GarbageCollectors";
    public static final long EXPIRATION_TIME = 864000000; //10 days
    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String REGISTER_PATIENT = "/patients/register";
    public static final String GET_ALL_PATIENTS = "/patients/all";
    public static final String GET_PATIENT_BY_ID = "/patients/get-by-id/{patientId}";
    public static final String ADD_HEALTH_DATA = "/health/add";
    public static final String FIND_HEALTH_DATA = "/health/find";
    public static final String UPDATE_HEALTH_DATA = "/health/update";
}

