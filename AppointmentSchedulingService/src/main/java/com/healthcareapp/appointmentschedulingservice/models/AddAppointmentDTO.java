package com.healthcareapp.appointmentschedulingservice.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddAppointmentDTO {
    private String doctorName;
    private String doctorId;
    private Integer serialNumber;
    private String problemDescription;
}
