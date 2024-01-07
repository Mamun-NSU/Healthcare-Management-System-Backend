package com.healthcareapp.pharmaceuticalinventorymanagementservice.services.interfaces;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.entities.MedicalEquipment;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.models.AddMedicalEquipmentDTO;
import java.util.List;
import java.util.UUID;

public interface EquipmentService {
    void addMedicalEquipment(AddMedicalEquipmentDTO addMedicalEquipmentDTO);
    List<MedicalEquipment> getAllEquipment();
    void updateEquipment(UUID equipmentId, AddMedicalEquipmentDTO medicalEquipmentDTO);
    boolean deleteEquipment(UUID equipmentId);
    MedicalEquipment getEquipmentById(UUID equipmentId);
    List<MedicalEquipment> getNonExpiredEquipments();
}
