package com.healthcareapp.pharmaceuticalinventorymanagementservice.services.interfaces;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.entities.EquipmentRoom;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.models.AddRoomDTO;
import java.util.List;
import java.util.UUID;

public interface EquipmentRoomService {
    void addEquipmentRoom(AddRoomDTO addRoomDTO);
    List<EquipmentRoom> getAllRooms();
    EquipmentRoom getRoomById(UUID roomId);
    void updateRoom(UUID roomId, AddRoomDTO updatedRoomDTO);
    boolean deleteRoom(UUID roomId);
}
