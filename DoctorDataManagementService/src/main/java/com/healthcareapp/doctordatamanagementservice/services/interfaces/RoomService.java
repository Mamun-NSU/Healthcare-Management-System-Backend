package com.healthcareapp.doctordatamanagementservice.services.interfaces;


import com.healthcareapp.doctordatamanagementservice.entities.Room;
import com.healthcareapp.doctordatamanagementservice.models.AddRoomDTO;

import java.util.List;

public interface RoomService {
    void addNewRoom(AddRoomDTO addRoomDTO);
    List<Room> getAllRooms();
}
