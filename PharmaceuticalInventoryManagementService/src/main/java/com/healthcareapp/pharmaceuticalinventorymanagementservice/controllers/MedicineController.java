package com.healthcareapp.pharmaceuticalinventorymanagementservice.controllers;

import com.healthcareapp.pharmaceuticalinventorymanagementservice.entities.Medicine;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.exceptions.DataNotFindByIdException;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.exceptions.NotAvailableException;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.models.AddMedicineDTO;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.models.MedicineQueryDTO;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.models.SuccessResponse;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.services.interfaces.MedicineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/medicines")
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @PostMapping("/add")
    public ResponseEntity<SuccessResponse> addMedicine(@RequestBody AddMedicineDTO addMedicineDTO) {
        medicineService.addMedicine(addMedicineDTO);

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("Medicine added successfully");

        return ResponseEntity.ok(successResponse);
    }

    @PutMapping("/update/{medicineId}")
    public ResponseEntity<SuccessResponse> updateMedicine(
            @PathVariable UUID medicineId,
            @RequestBody AddMedicineDTO updatedMedicineDTO
    ) {
        try {
            medicineService.updateMedicine(medicineId, updatedMedicineDTO);

            SuccessResponse successResponse = new SuccessResponse();
            successResponse.setMessage("Medicine updated successfully");

            return ResponseEntity.ok(successResponse);
        } catch (DataNotFindByIdException ex) {
            // Exception handling is done by GlobalExceptionHandler
            throw ex;
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        List<Medicine> medicines = medicineService.getAllMedicines();
        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/{medicineId}")
    public ResponseEntity<?> getMedicineById(@PathVariable UUID medicineId) {
        Medicine medicine = medicineService.getMedicineById(medicineId);
        if (medicine != null) {
            return ResponseEntity.ok(medicine);
        } else {
            throw new DataNotFindByIdException("No Medicine found for this medicineId");
        }
    }

    @GetMapping("/search-by-category")
    public ResponseEntity<List<Medicine>> getMedicineByCategoryAndNotExpired(
            @RequestBody MedicineQueryDTO medicineQueryDTO
    ) {
        List<Medicine> medicines = medicineService.getMedicineByCategoryAndNotExpired(medicineQueryDTO);
        return ResponseEntity.ok(medicines);
    }

    @PutMapping("/update-expired")
    public ResponseEntity<SuccessResponse> updateExpiredMedicine() {
        medicineService.updateExpiredAuto();

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("Medicine updated successfully");

        return ResponseEntity.ok(successResponse);
    }

    @DeleteMapping("/delete/{medicineId}")
    public ResponseEntity<?> deleteMedicine(@PathVariable UUID medicineId) {
        try {
            boolean deleted = medicineService.deleteMedicine(medicineId);

            if (deleted) {
                SuccessResponse successResponse = new SuccessResponse();
                successResponse.setMessage("Medicine deleted successfully");
                return ResponseEntity.ok(successResponse);
            } else {
                throw new DataNotFindByIdException("No Medicine found with this medicineId");
            }
        } catch (DataNotFindByIdException ex) {
            // Exception handling is done by GlobalExceptionHandler
            throw ex;
        } catch (NotAvailableException ex) {
            // Exception handling is done by GlobalExceptionHandler
            throw ex;
        }
    }
}
