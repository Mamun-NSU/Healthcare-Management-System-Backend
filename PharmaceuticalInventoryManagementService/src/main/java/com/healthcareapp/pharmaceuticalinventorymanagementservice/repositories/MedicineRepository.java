package com.healthcareapp.pharmaceuticalinventorymanagementservice.repositories;

import com.healthcareapp.pharmaceuticalinventorymanagementservice.entities.Medicine;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, UUID> {
    List<Medicine> findByCategoryAndExpiredFalse(Category category);
    @Modifying
    @Query("UPDATE Medicine m SET m.expired = true WHERE m.expirationDate < CURRENT_DATE")
    void updateExpiredAuto();
}