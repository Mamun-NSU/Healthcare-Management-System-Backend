package com.healthcareapp.pharmaceuticalinventorymanagementservice.builders;

import com.healthcareapp.pharmaceuticalinventorymanagementservice.entities.Medicine;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.enums.Category;

import java.util.Date;

public class MedicineBuilder {

    private String medicineName;
    private Category category;
    private Integer dosage;
    private String manufacturedBy;
    private Date expirationDate;
    private int quantity;
    private String sideEffects;
    private Boolean expired;

    public MedicineBuilder withMedicineName(String medicineName) {
        this.medicineName = medicineName;
        return this;
    }

    public MedicineBuilder withCategory(Category category) {
        this.category = category;
        return this;
    }

    public MedicineBuilder withDosage(Integer dosage) {
        this.dosage = dosage;
        return this;
    }

    public MedicineBuilder withManufacturedBy(String manufacturedBy) {
        this.manufacturedBy = manufacturedBy;
        return this;
    }

    public MedicineBuilder withExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public MedicineBuilder withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public MedicineBuilder withSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
        return this;
    }

    public MedicineBuilder withExpired(Boolean expired) {
        this.expired = expired;
        return this;
    }

    public Medicine build() {
        Medicine medicine = new Medicine();
        medicine.setMedicineName(medicineName);
        medicine.setCategory(category);
        medicine.setDosage(dosage);
        medicine.setManufacturedBy(manufacturedBy);
        medicine.setExpirationDate(expirationDate);
        medicine.setQuantity(quantity);
        medicine.setSideEffects(sideEffects);
        medicine.setExpired(expired);
        return medicine;
    }
}

