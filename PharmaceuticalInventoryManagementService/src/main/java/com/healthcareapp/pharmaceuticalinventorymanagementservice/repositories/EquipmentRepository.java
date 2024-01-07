package com.healthcareapp.pharmaceuticalinventorymanagementservice.repositories;

import com.healthcareapp.pharmaceuticalinventorymanagementservice.entities.MedicalEquipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EquipmentRepository extends JpaRepository<MedicalEquipment, UUID> {
    List<MedicalEquipment> findByExpiredFalse();

}
