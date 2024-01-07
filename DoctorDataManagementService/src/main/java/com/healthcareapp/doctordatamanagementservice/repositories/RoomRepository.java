package com.healthcareapp.doctordatamanagementservice.repositories;

import com.healthcareapp.doctordatamanagementservice.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {
}
