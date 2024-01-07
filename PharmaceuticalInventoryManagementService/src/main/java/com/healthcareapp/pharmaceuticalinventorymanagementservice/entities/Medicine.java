package com.healthcareapp.pharmaceuticalinventorymanagementservice.entities;

import com.healthcareapp.pharmaceuticalinventorymanagementservice.enums.Category;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "medicine_id", updatable = false, nullable = false)
    private UUID medicineId;

    private String medicineName;

    @Enumerated(EnumType.STRING)

    @NotNull(message = "Category can not be empty")
    @Column(name = "category")
    private Category category;
    @Column(name = "dosage")
    @NotNull(message = "Please provide dosage")
    private int dosage;

    @Column(name = "manufactured_by")
    private String manufacturedBy;
    @Column(name = "expiration_date")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    @Column(name = "quantity")
    @NotNull(message = "quantity can not be empty")
    private int quantity;

    @Lob
    @Column(name ="side_effects")
    private String sideEffects;

    @Column(name = "expired")
    private Boolean expired;
}

