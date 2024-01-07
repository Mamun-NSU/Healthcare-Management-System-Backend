package com.healthcareapp.pharmaceuticalinventorymanagementservice.models;

import com.healthcareapp.pharmaceuticalinventorymanagementservice.enums.Category;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddMedicineDTO {
    private String medicineName;
    private int dosage;
    private Category category;
    private String manufacturedBy;
    private Date expirationDate;
    private int quantity;
    private String sideEffects;
    private Boolean expired;
}

