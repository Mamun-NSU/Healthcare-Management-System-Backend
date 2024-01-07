package com.healthcareapp.pharmaceuticalinventorymanagementservice.services.implementations;

import com.healthcareapp.pharmaceuticalinventorymanagementservice.entities.EquipmentRoom;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.enums.RoomStatus;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.exceptions.DataNotFindByIdException;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.models.AddRoomDTO;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.repositories.EquipmentRoomRepository;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.services.interfaces.EquipmentRoomService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EquipmentRoomServiceImpl implements EquipmentRoomService {

    private final EquipmentRoomRepository equipmentRoomRepository;

    public EquipmentRoomServiceImpl(EquipmentRoomRepository equipmentRoomRepository) {
        this.equipmentRoomRepository = equipmentRoomRepository;
    }

    @Override
    public void addEquipmentRoom(AddRoomDTO addRoomDTO) {
        EquipmentRoom equipmentRoom = new EquipmentRoom();
        equipmentRoom.setRoomNo(addRoomDTO.getRoomNo());
        equipmentRoom.setRoomStatus(RoomStatus.FREE);
        equipmentRoomRepository.save(equipmentRoom);
    }

    @Override
    public List<EquipmentRoom> getAllRooms() {
        return equipmentRoomRepository.findAll();
    }

    @Override
    public EquipmentRoom getRoomById(UUID roomId) {
        return equipmentRoomRepository.findById(roomId).orElse(null);
    }

    @Override
    public void updateRoom(UUID roomId, AddRoomDTO updatedRoomDTO) {
        EquipmentRoom room = equipmentRoomRepository.findById(roomId)
                .orElseThrow(() -> new DataNotFindByIdException("No Room found with this roomId"));

        // Update room fields based on the updatedRoomDTO

        equipmentRoomRepository.save(room);
    }

    @Override
    public boolean deleteRoom(UUID roomId) {
        if (equipmentRoomRepository.existsById(roomId)) {
            equipmentRoomRepository.deleteById(roomId);
            return true;
        }
        return false;
    }
}
