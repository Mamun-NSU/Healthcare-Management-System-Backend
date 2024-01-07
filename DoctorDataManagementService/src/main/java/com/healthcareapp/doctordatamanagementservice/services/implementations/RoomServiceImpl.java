package com.healthcareapp.doctordatamanagementservice.services.implementations;

import com.healthcareapp.doctordatamanagementservice.entities.Room;
import com.healthcareapp.doctordatamanagementservice.enums.RoomStatus;
import com.healthcareapp.doctordatamanagementservice.models.AddRoomDTO;
import com.healthcareapp.doctordatamanagementservice.repositories.RoomRepository;
import com.healthcareapp.doctordatamanagementservice.services.interfaces.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void addNewRoom(AddRoomDTO addRoomDTO) {
        Room room = new Room();
        room.setRoomNo(addRoomDTO.getRoomNo());
        room.setRoomStatus(RoomStatus.Free);
        roomRepository.save(room);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
}
