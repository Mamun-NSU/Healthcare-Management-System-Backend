package com.healthcareapp.pharmaceuticalinventorymanagementservice.services.implementations;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.entities.Medicine;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.models.AddMedicineDTO;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.models.MedicineQueryDTO;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.repositories.MedicineRepository;
import com.healthcareapp.pharmaceuticalinventorymanagementservice.services.interfaces.MedicineService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
@Service
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepository;
    public MedicineServiceImpl(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }
    @Override
    public void addMedicine(AddMedicineDTO addMedicineDTO) {
        Medicine medicine = new Medicine();
        medicine.setMedicineName(addMedicineDTO.getMedicineName());
        medicine.setCategory(addMedicineDTO.getCategory());
        medicine.setDosage(addMedicineDTO.getDosage());
        medicine.setManufacturedBy(addMedicineDTO.getManufacturedBy());
        medicine.setExpirationDate(addMedicineDTO.getExpirationDate());
        medicine.setQuantity(addMedicineDTO.getQuantity());
        medicine.setSideEffects(addMedicineDTO.getSideEffects());
        medicine.setExpired(false);
        medicineRepository.save(medicine);
    }
    @Override
    public void updateMedicine(UUID medicineId, AddMedicineDTO updatedMedicineDTO) {
        Medicine existingMedicine = medicineRepository.findById(medicineId)
                .orElseThrow(() -> new RuntimeException("Medicine not found with ID: " + medicineId));

        updateFieldIfNotNull(updatedMedicineDTO.getMedicineName(), existingMedicine::setMedicineName);
        updateFieldIfNotNull(updatedMedicineDTO.getCategory(), existingMedicine::setCategory);
        updateFieldIfNotNull(updatedMedicineDTO.getDosage(), existingMedicine::setDosage);
        updateFieldIfNotNull(updatedMedicineDTO.getManufacturedBy(), existingMedicine::setManufacturedBy);
        updateFieldIfNotNull(updatedMedicineDTO.getExpirationDate(), existingMedicine::setExpirationDate);
        updateFieldIfNotNull(updatedMedicineDTO.getQuantity(), existingMedicine::setQuantity);
        updateFieldIfNotNull(updatedMedicineDTO.getSideEffects(), existingMedicine::setSideEffects);
        updateFieldIfNotNull(updatedMedicineDTO.getExpired(), existingMedicine::setExpired);
        medicineRepository.save(existingMedicine);
    }

    private <T> void updateFieldIfNotNull(T newValue, Consumer<T> updateFunction) {
        if (newValue != null) {
            updateFunction.accept(newValue);
        }
    }
    @Override
    public boolean deleteMedicine(UUID medicineId) {
        try {
            medicineRepository.deleteById(medicineId);
            return true;
        } catch (EmptyResultDataAccessException ex) {
            return false;
        }
    }
    @Override
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }
    @Override
    public Medicine getMedicineById(UUID medicineId) {
        return medicineRepository.findById(medicineId)
                .orElse(null);
    }
    @Override
    public List<Medicine> getMedicineByCategoryAndNotExpired(MedicineQueryDTO medicineQueryDTO) {
        return medicineRepository.findByCategoryAndExpiredFalse(medicineQueryDTO.getCategory());
    }
    @Override
    public void updateExpiredAuto() {
        medicineRepository.updateExpiredAuto();
    }

}
