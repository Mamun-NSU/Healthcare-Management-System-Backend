package com.healthcareapp.pharmaceuticalinventorymanagementservice.builders;

import com.healthcareapp.pharmaceuticalinventorymanagementservice.entities.EquipmentRoom;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.entities.MedicalEquipment;

import java.util.Date;

public class MedicalEquipmentBuilder {

    private String name;
    private String description;
    private String manufacturer;
    private Date purchaseDate;
    private Boolean expired;
    private EquipmentRoom equipmentRoom;

    public MedicalEquipmentBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public MedicalEquipmentBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public MedicalEquipmentBuilder withManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public MedicalEquipmentBuilder withPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
    }

    public MedicalEquipmentBuilder withExpired(Boolean expired) {
        this.expired = expired;
        return this;
    }

    public MedicalEquipmentBuilder withEquipmentRoom(EquipmentRoom equipmentRoom) {
        this.equipmentRoom = equipmentRoom;
        return this;
    }

    public MedicalEquipment build() {
        MedicalEquipment medicalEquipment = new MedicalEquipment();
        medicalEquipment.setName(name);
        medicalEquipment.setDescription(description);
        medicalEquipment.setManufacturer(manufacturer);
        medicalEquipment.setPurchaseDate(purchaseDate);
        medicalEquipment.setExpired(expired);
        medicalEquipment.setEquipmentRoom(equipmentRoom);
        return medicalEquipment;
    }
}

