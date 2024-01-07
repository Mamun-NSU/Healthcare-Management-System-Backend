package com.healthcareapp.pharmaceuticalinventorymanagementservice.controllers;

import com.healthcareapp.pharmaceuticalinventorymanagementservice.entities.MedicalEquipment;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.exceptions.DataNotFindByIdException;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.models.AddMedicalEquipmentDTO;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.models.SuccessResponse;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.services.interfaces.EquipmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PostMapping("/add")
    public ResponseEntity<SuccessResponse> addMedicalEquipment(@RequestBody AddMedicalEquipmentDTO addMedicalEquipmentDTO) {
        equipmentService.addMedicalEquipment(addMedicalEquipmentDTO);

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("Medical equipment added successfully");

        return ResponseEntity.ok(successResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MedicalEquipment>> getAllEquipment() {
        List<MedicalEquipment> equipmentList = equipmentService.getAllEquipment();
        return ResponseEntity.ok(equipmentList);
    }

    @PutMapping("/update/{equipmentId}")
    public ResponseEntity<SuccessResponse> updateEquipment(
            @PathVariable UUID equipmentId,
            @RequestBody AddMedicalEquipmentDTO medicalEquipmentDTO
    ) {
        equipmentService.updateEquipment(equipmentId, medicalEquipmentDTO);

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("Equipment updated successfully");

        return ResponseEntity.ok(successResponse);
    }

    @GetMapping("/non-expired")
    public ResponseEntity<List<MedicalEquipment>> getNonExpiredEquipments() {
        List<MedicalEquipment> nonExpiredEquipments = equipmentService.getNonExpiredEquipments();
        return new ResponseEntity<>(nonExpiredEquipments, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{equipmentId}")
    public ResponseEntity<?> deleteEquipment(@PathVariable UUID equipmentId) {
        try {
            boolean deleted = equipmentService.deleteEquipment(equipmentId);

            if (deleted) {
                SuccessResponse successResponse = new SuccessResponse();
                successResponse.setMessage("Equipment deleted successfully");
                return ResponseEntity.ok(successResponse);
            } else {
                throw new DataNotFindByIdException("No Equipment found with this equipmentId");
            }
        } catch (DataNotFindByIdException ex) {
            // Exception handling is done by GlobalExceptionHandler
            throw ex;
        }
    }

    @GetMapping("/{equipmentId}")
    public ResponseEntity<?> getEquipmentById(@PathVariable UUID equipmentId) {
        MedicalEquipment equipment = equipmentService.getEquipmentById(equipmentId);
        if (equipment != null) {
            return ResponseEntity.ok(equipment);
        } else {
            throw new DataNotFindByIdException("No Equipment found for this equipmentId");
        }
    }
}
