package com.healthcareapp.pharmaceuticalinventorymanagementservice;

import com.healthcareapp.pharmaceuticalinventorymanagementservice.builders.EquipmentRoomBuilder;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.builders.MedicalEquipmentBuilder;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.builders.MedicineBuilder;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.controllers.MedicineController;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.entities.EquipmentRoom;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.entities.MedicalEquipment;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.entities.Medicine;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.enums.Category;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.enums.RoomStatus;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.models.AddMedicineDTO;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.models.AddRoomDTO;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.repositories.EquipmentRepository;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.repositories.EquipmentRoomRepository;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.repositories.MedicineRepository;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.services.implementations.EquipmentRoomServiceImpl;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.services.implementations.EquipmentServiceImpl;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.services.implementations.MedicineServiceImpl;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.services.interfaces.EquipmentService;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.services.interfaces.MedicineService;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.builders.MedicalEquipmentBuilder;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.builders.MedicineBuilder;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.entities.EquipmentRoom;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.entities.MedicalEquipment;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.entities.Medicine;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.enums.Category;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.enums.RoomStatus;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.models.AddMedicineDTO;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.models.AddRoomDTO;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.repositories.EquipmentRoomRepository;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.repositories.MedicineRepository;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.services.implementations.EquipmentRoomServiceImpl;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.services.implementations.EquipmentServiceImpl;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.services.implementations.MedicineServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class PharmaceuticalInventoryServiceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Mock
    private EquipmentRoomRepository equipmentRoomRepository;

    @InjectMocks
    private EquipmentRoomServiceImpl equipmentRoomService;

    @Mock
    private MedicineRepository medicineRepository;

    @InjectMocks
    private MedicineServiceImpl medicineService;
    @Mock
    private EquipmentRoomRepository equipmentRepository;
    @InjectMocks
    private EquipmentServiceImpl equipmentService;

    @Test
    void testAddEquipmentRoom() {
        AddRoomDTO addRoomDTO = new AddRoomDTO();
        addRoomDTO.setRoomNo(101);
        equipmentRoomService.addEquipmentRoom(addRoomDTO);
        ArgumentCaptor<EquipmentRoom> equipmentRoomCaptor = ArgumentCaptor.forClass(EquipmentRoom.class);
        verify(equipmentRoomRepository).save(equipmentRoomCaptor.capture());
        EquipmentRoom savedEquipmentRoom = equipmentRoomCaptor.getValue();
        assertEquals(101, savedEquipmentRoom.getRoomNo());
        assertEquals(RoomStatus.FREE, savedEquipmentRoom.getRoomStatus());
    }

    @Test
    public void testGetAllMedicines() {
        Medicine medicine1 = new MedicineBuilder()
                .withMedicineName("Medicine1")
                .withCategory(Category.DROPS)
                .withDosage(5)
                .withManufacturedBy("Manufacturer1")
                .withExpirationDate(new Date())
                .withQuantity(50)
                .withSideEffects("Side effects for Medicine1")
                .withExpired(false)
                .build();

        Medicine medicine2 = new MedicineBuilder()
                .withMedicineName("Medicine2")
                .withCategory(Category.DROPS)
                .withDosage(10)
                .withManufacturedBy("Manufacturer2")
                .withExpirationDate(new Date())
                .withQuantity(40)
                .withSideEffects("Side effects for Medicine2")
                .withExpired(false)
                .build();
        List<Medicine> mockMedicines = Arrays.asList(medicine1, medicine2);
        when(medicineRepository.findAll()).thenReturn(mockMedicines);
        List<Medicine> result = medicineService.getAllMedicines();
        assertEquals(2, result.size());
        assertEquals(medicine1, result.get(0));
        assertEquals(medicine2, result.get(1));
        verify(medicineRepository, times(1)).findAll();
    }

    @Test
    public void testAddMedicine() {
        AddMedicineDTO addMedicineDTO = new AddMedicineDTO();
        addMedicineDTO.setMedicineName("MedicineName");
        addMedicineDTO.setCategory(Category.SYRUP);
        addMedicineDTO.setDosage(10);
        addMedicineDTO.setManufacturedBy("Manufacturer");
        addMedicineDTO.setExpirationDate(new Date());
        addMedicineDTO.setQuantity(50);
        addMedicineDTO.setSideEffects("Drowsiness, dry mouth");
        medicineService.addMedicine(addMedicineDTO);
        ArgumentCaptor<Medicine> medicineCaptor = ArgumentCaptor.forClass(Medicine.class);
        verify(medicineRepository).save(medicineCaptor.capture());
        Medicine capturedMedicine = medicineCaptor.getValue();
        assertEquals("MedicineName", capturedMedicine.getMedicineName());
        assertEquals(Category.SYRUP, capturedMedicine.getCategory());
    }
    @Test
    public void testGetMedicineByIdNotFound() {
        UUID medicineId = UUID.randomUUID();
        when(medicineRepository.findById(medicineId)).thenReturn(Optional.empty());
        Medicine result = medicineService.getMedicineById(medicineId);
        assertNull(result);
        verify(medicineRepository, times(1)).findById(medicineId);
    }
    @Test
    public void testGetEquipmentByIdNotFound() {
        UUID equipmentId = UUID.randomUUID();
        when(equipmentRepository.findById(equipmentId)).thenReturn(Optional.empty());
        MedicalEquipment result = equipmentService.getEquipmentById(equipmentId);
        assertNull(result);
        verify(equipmentRepository, times(1)).findById(equipmentId);
    }
    @Test
    public void testGetAllEquipment() {
        List<MedicalEquipment> expectedEquipmentList = Arrays.asList(
                new MedicalEquipmentBuilder().build(),
                new MedicalEquipmentBuilder().build()
        );
        when(equipmentRepository.findAll()).thenReturn(expectedEquipmentList);
        List<MedicalEquipment> result = equipmentService.getAllEquipment();
        assertEquals(expectedEquipmentList, result);
        verify(equipmentRepository, times(1)).findAll();
    }
    @Test
    public void testGetAllEquipmentEmptyList() {
        List<MedicalEquipment> expectedEquipmentList = Collections.emptyList();
        when(equipmentRepository.findAll()).thenReturn(expectedEquipmentList);
        List<MedicalEquipment> result = equipmentService.getAllEquipment();
        assertTrue(result.isEmpty());
        verify(equipmentRepository, times(1)).findAll();
    }
    @Test
    public void testGetAllEquipmentNullList() {
        when(equipmentRepository.findAll()).thenReturn(null);
        List<MedicalEquipment> result = equipmentService.getAllEquipment();
        assertNull(result);
        verify(equipmentRepository, times(1)).findAll();
    }
    @Test
    public void testGetAllRoomsWithEmptyList() {
        when(equipmentRoomRepository.findAll()).thenReturn(Collections.emptyList());
        List<EquipmentRoom> result = equipmentRoomService.getAllRooms();
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(equipmentRoomRepository, times(1)).findAll();
    }
    @Test
    public void testGetAllRooms() {
        List<EquipmentRoom> expectedRooms = Arrays.asList(
                new EquipmentRoomBuilder().build(),
                new EquipmentRoomBuilder().build()
        );
        when(equipmentRoomRepository.findAll()).thenReturn(expectedRooms);
        List<EquipmentRoom> result = equipmentRoomService.getAllRooms();
        assertEquals(expectedRooms, result);
        verify(equipmentRoomRepository, times(1)).findAll();
    }

}
