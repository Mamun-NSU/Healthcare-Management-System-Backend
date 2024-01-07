package com.healthcareapp.pharmaceuticalinventorymanagementservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "medical_equipment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "equipment_id", updatable = false, nullable = false)
    private UUID equipmentId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "purchase_date")
    @Temporal(TemporalType.DATE)
    private Date purchaseDate;
    @Column(name = "expired")
    private Boolean expired;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private EquipmentRoom equipmentRoom;
}

