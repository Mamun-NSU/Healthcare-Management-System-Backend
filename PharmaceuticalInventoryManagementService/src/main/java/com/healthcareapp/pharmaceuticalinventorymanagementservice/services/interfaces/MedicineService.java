package com.healthcareapp.pharmaceuticalinventorymanagementservice.services.interfaces;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.entities.Medicine;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.models.AddMedicineDTO;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.models.MedicineQueryDTO;
import java.util.List;
import java.util.UUID;

public interface MedicineService {

    void addMedicine(AddMedicineDTO addMedicineDTO);

    void updateMedicine(UUID medicineId, AddMedicineDTO updatedMedicineDTO);

    List<Medicine> getAllMedicines();

    Medicine getMedicineById(UUID medicineId);
    List<Medicine> getMedicineByCategoryAndNotExpired(MedicineQueryDTO medicineQueryDTO);

    void updateExpiredAuto();

    boolean deleteMedicine(UUID medicineId);


}

