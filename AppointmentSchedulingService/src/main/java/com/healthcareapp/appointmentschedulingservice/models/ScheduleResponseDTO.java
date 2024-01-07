package com.healthcareapp.appointmentschedulingservice.models;
import com.healthcareapp.appointmentschedulingservice.enums.AppointmentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleResponseDTO {
    private String dfirstname;
    private String dlastName;
    private Date availableDay;
    private Time startTime;
    private Integer timePerPatient;
    private Integer patientNumbers;
    private AppointmentType appointmentType;
}
