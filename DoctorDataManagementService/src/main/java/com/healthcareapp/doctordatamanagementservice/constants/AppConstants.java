package com.healthcareapp.doctordatamanagementservice.constants;

public class AppConstants {
    public static final String TOKEN_SECRET = "SM2P_GarbageCollectors";
    public static final long EXPIRATION_TIME = 864000000; //10 days
    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String REGISTER_DOCTOR = "/doctors/register";
    public static final String GET_ALL_DOCTORS = "/doctors/all";
    public static final String GET_DOCTOR_BY_ID = "/doctors/get-by-id/{doctorId}";
    public static final String FILTER_DOCTORS = "/doctors/filter";
    public static final String UPDATE_DOCTOR = "/doctors/update-doctor";
    public static final String UPDATE_DOCTOR_APPROVAL = "/doctors/update-approval/{doctorId}";
    public static final String ADD_ROOM = "/doctor-rooms/add";
    public static final String GET_ALL_ROOMS = "/doctor-rooms/all";
}

