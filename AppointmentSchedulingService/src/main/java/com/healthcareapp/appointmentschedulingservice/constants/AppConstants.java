package com.healthcareapp.appointmentschedulingservice.constants;

public class AppConstants {
    public static final String TOKEN_SECRET = "SM2P_GarbageCollectors";
    public static final long EXPIRATION_TIME = 864000000; //10 days
    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String SCHEDULE_CREATE = "/doctors/schedule";
    public static final String SCHEDULE_UPDATE ="/doctors/schedule/update-appointment";
    public static final String ONGOING_SCHEDULE_CHANGE = "/doctors/schedule/change-ongoing-status/{doctorId}";
    public static final String OVER_STATUS_CHANGE = "/doctors/schedule/change-over-status/{doctorId}";
    public static final String APPOINTMENT_HAPPENED_CHANGE = "/appointments/update-status/{doctorId}";
    public static final String SCHEDULE_CANCEL = "/doctors/schedule/cancel/{doctorId}";
    public static final String APPOINTMENT_CANCEL = "/appointments/cancel-appointment";
    public static final String APPOINTMENT_ADD = "/appointments/add";
}
