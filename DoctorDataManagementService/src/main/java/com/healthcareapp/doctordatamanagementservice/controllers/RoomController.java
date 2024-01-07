package com.healthcareapp.doctordatamanagementservice.controllers;

import com.healthcareapp.doctordatamanagementservice.entities.Room;
import com.healthcareapp.doctordatamanagementservice.models.AddRoomDTO;
import com.healthcareapp.doctordatamanagementservice.services.interfaces.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor-rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewRoom(@RequestBody AddRoomDTO addRoomDTO) {
        roomService.addNewRoom(addRoomDTO);
        return new ResponseEntity<>("Room added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }
}

