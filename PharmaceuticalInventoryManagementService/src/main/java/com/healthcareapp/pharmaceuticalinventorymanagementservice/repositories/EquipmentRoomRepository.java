package com.healthcareapp.pharmaceuticalinventorymanagementservice.repositories;

import com.healthcareapp.pharmaceuticalinventorymanagementservice.entities.EquipmentRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EquipmentRoomRepository extends JpaRepository<EquipmentRoom, UUID> {
}
