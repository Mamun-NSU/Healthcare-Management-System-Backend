package com.healthcareapp.pharmaceuticalinventorymanagementservice.services.implementations;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.entities.EquipmentRoom;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.entities.MedicalEquipment;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.enums.RoomStatus;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.exceptions.DataNotFindByIdException;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.exceptions.NotAvailableException;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.models.AddMedicalEquipmentDTO;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.repositories.EquipmentRepository;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.repositories.EquipmentRoomRepository;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.services.interfaces.EquipmentService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
@Service
public class EquipmentServiceImpl implements EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final EquipmentRoomRepository equipmentRoomRepository;
    public EquipmentServiceImpl(EquipmentRepository equipmentRepository,
                                EquipmentRoomRepository equipmentRoomRepository) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentRoomRepository = equipmentRoomRepository;
    }
    @Override
    public void addMedicalEquipment(AddMedicalEquipmentDTO addMedicalEquipmentDTO) {
        MedicalEquipment newEquipment = new MedicalEquipment();
        List<EquipmentRoom> roomList = equipmentRoomRepository.findAll();
        newEquipment.setName(addMedicalEquipmentDTO.getName());
        newEquipment.setDescription(addMedicalEquipmentDTO.getDescription());
        newEquipment.setPurchaseDate(addMedicalEquipmentDTO.getPurchaseDate());
        newEquipment.setManufacturer(addMedicalEquipmentDTO.getManufacturer());
        newEquipment.setExpired(false);
        EquipmentRoom availableRoom = roomList.stream()
                .filter(room -> room.getRoomStatus() == RoomStatus.FREE)
                .findFirst()
                .orElse(null);
        if (availableRoom != null) {
            newEquipment.setEquipmentRoom(availableRoom);
            availableRoom.setRoomStatus(RoomStatus.OCCUPIED);
            equipmentRoomRepository.save(availableRoom);
        } else {
            throw new NotAvailableException("Error! No room is available for this equipment.");
        }
        equipmentRepository.save(newEquipment);
    }
    @Override
    public List<MedicalEquipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }
    @Override
    public void updateEquipment(UUID equipmentId, AddMedicalEquipmentDTO medicalEquipmentDTO) {
        MedicalEquipment existingEquipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new RuntimeException("Equipment not found with ID: " + equipmentId));

        if (medicalEquipmentDTO.getName() != null && !medicalEquipmentDTO.getName().isEmpty()) {
            existingEquipment.setName(medicalEquipmentDTO.getName());
        }
        if (medicalEquipmentDTO.getDescription() != null && !medicalEquipmentDTO.getDescription().isEmpty()) {
            existingEquipment.setDescription(medicalEquipmentDTO.getDescription());
        }
        if (medicalEquipmentDTO.getPurchaseDate() != null) {
            existingEquipment.setPurchaseDate(medicalEquipmentDTO.getPurchaseDate());
        }
        if (medicalEquipmentDTO.getManufacturer() != null && !medicalEquipmentDTO.getManufacturer().isEmpty()) {
            existingEquipment.setManufacturer(medicalEquipmentDTO.getManufacturer());
        }
        // Update the expired status only if it is provided in the DTO
        if (medicalEquipmentDTO.getExpired() != null) {
            existingEquipment.setExpired(medicalEquipmentDTO.getExpired());
        }
        equipmentRepository.save(existingEquipment);
    }
    @Override
    public boolean deleteEquipment(UUID equipmentId) {
        MedicalEquipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new DataNotFindByIdException("Equipment not found with ID: " + equipmentId));
        // Free up the room when deleting the equipment
        EquipmentRoom equipmentRoom = equipment.getEquipmentRoom();
        if (equipmentRoom != null) {
            equipmentRoom.setRoomStatus(RoomStatus.FREE);
            equipmentRoomRepository.save(equipmentRoom);
        }
        equipmentRepository.deleteById(equipmentId);
        return true;
    }
    @Override
    public MedicalEquipment getEquipmentById(UUID equipmentId) {
        return equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new DataNotFindByIdException("Equipment not found with ID: " + equipmentId));
    }
    @Override
    public List<MedicalEquipment> getNonExpiredEquipments() {
        return equipmentRepository.findByExpiredFalse();
    }

}
