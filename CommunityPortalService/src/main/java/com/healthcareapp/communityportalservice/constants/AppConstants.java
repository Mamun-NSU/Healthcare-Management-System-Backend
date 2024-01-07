package com.healthcareapp.communityportalservice.constants;

public class AppConstants {
    public static final String TOKEN_SECRET = "SM2P_GarbageCollectors";
    public static final long EXPIRATION_TIME = 864000000; //10 days
    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String ADD_REVIEW = "/reviews/add/{patientId}";
    public static final String GET_ALL_REVIEWS = "/reviews/all";
    public static final String DELETE_REVIEW = "/reviews/delete/{patientId}";
    public static final String UPDATE_REVIEW = "/reviews/update/{patientId}";
    public static final String ADD_POST = "/posts/add/{patientId}";
    public static final String GET_ALL_POST = "/posts/all";
    public static final String GET_BYID_POST = "/posts/{patientId}";
    public static final String DELETE_POST = "/posts/delete/{patientId}";
    public static final String UPDATE_POST = "/posts/update/{patientId}";
    public static final String FIND_REVIEW_BY_PATIENT_ID = "/reviews/find/{patientId}";
    public static final String UPDATE_PROGRESS_CHECK = "/progress/update/{patientId}";
    public static final String CHECK_PROGRESS = "/progress/check/{patientId}";
    public static final String GET_PROGRESS_BY_ID = "/progress/get-by-id";
}

