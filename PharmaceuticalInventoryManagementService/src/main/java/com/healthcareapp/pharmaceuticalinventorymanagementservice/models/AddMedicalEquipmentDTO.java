package com.healthcareapp.pharmaceuticalinventorymanagementservice.models;

import lombok.*;

import java.util.Date;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddMedicalEquipmentDTO {
    private String name;
    private String description;
    private Date purchaseDate;
    private String manufacturer;
    private Boolean expired;
}