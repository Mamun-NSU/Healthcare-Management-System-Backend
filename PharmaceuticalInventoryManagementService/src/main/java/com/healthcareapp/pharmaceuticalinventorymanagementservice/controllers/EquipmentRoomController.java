package com.healthcareapp.pharmaceuticalinventorymanagementservice.controllers;

import com.healthcareapp.pharmaceuticalinventorymanagementservice.entities.EquipmentRoom;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.exceptions.DataNotFindByIdException;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.models.AddRoomDTO;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.models.SuccessResponse;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.services.interfaces.EquipmentRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/equipment-rooms")
public class EquipmentRoomController {

    private final EquipmentRoomService equipmentRoomService;

    @Autowired
    public EquipmentRoomController(EquipmentRoomService equipmentRoomService) {
        this.equipmentRoomService = equipmentRoomService;
    }

    @PostMapping("/add")
    public ResponseEntity<SuccessResponse> addEquipmentRoom(@RequestBody AddRoomDTO addRoomDTO) {
        equipmentRoomService.addEquipmentRoom(addRoomDTO);

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("Equipment room added successfully");

        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<EquipmentRoom>> getAllRooms() {
        List<EquipmentRoom> rooms = equipmentRoomService.getAllRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<?> getRoomById(@PathVariable UUID roomId) {
        try {
            EquipmentRoom room = equipmentRoomService.getRoomById(roomId);
            if (room != null) {
                return ResponseEntity.ok(room);
            } else {
                throw new DataNotFindByIdException("No Room found with this roomId");
            }
        } catch (DataNotFindByIdException ex) {
            // Exception handling is done by GlobalExceptionHandler
            throw ex;
        }
    }

    @PutMapping("/update/{roomId}")
    public ResponseEntity<SuccessResponse> updateRoom(
            @PathVariable UUID roomId,
            @RequestBody AddRoomDTO updatedRoomDTO
    ) {
        try {
            equipmentRoomService.updateRoom(roomId, updatedRoomDTO);

            SuccessResponse successResponse = new SuccessResponse();
            successResponse.setMessage("Room updated successfully");

            return ResponseEntity.ok(successResponse);
        } catch (DataNotFindByIdException ex) {
            // Exception handling is done by GlobalExceptionHandler
            throw ex;
        }
    }

    @DeleteMapping("/delete/{roomId}")
    public ResponseEntity<?> deleteRoom(@PathVariable UUID roomId) {
        try {
            boolean deleted = equipmentRoomService.deleteRoom(roomId);

            if (deleted) {
                SuccessResponse successResponse = new SuccessResponse();
                successResponse.setMessage("Room deleted successfully");
                return ResponseEntity.ok(successResponse);
            } else {
                throw new DataNotFindByIdException("No Room found with this roomId");
            }
        } catch (DataNotFindByIdException ex) {
            // Exception handling is done by GlobalExceptionHandler
            throw ex;
        }
    }
}

