package com.example.eMedicineStore.serviceImpl;

import com.example.eMedicineStore.entity.Medicine;
import com.example.eMedicineStore.exception.MedicineNotFoundException;
import com.example.eMedicineStore.repository.MedicineRepository;
import com.example.eMedicineStore.service.MedicineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineServiceImpl implements MedicineService {
    public static final Logger logger = LoggerFactory.getLogger(MedicineServiceImpl.class);

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public Medicine getMedicineById(Long medicineId) {
        logger.info("Got medicine by ID successfully!");
        return medicineRepository.findById(medicineId).get();
    }

    @Override
    public Medicine addMedicine(Medicine medicine) {
        logger.info("Saved medicine successfully!");
        return medicineRepository.save(medicine);
    }

    @Override
    public Medicine updateMedicineById(Medicine medicine, Long medicineId) throws MedicineNotFoundException {
        Optional<Medicine> medicine1 = medicineRepository.findById(medicineId);
        if (medicine1.isPresent()){
            //update code
            if (!medicine1.get().getMedicineName().equals(medicine.getMedicineName())){
                medicine1.get().setMedicineName(medicine.getMedicineName());
            }
            if (!medicine1.get().getDescription().equals(medicine.getDescription())){
                medicine1.get().setDescription(medicine.getDescription());
            }
            if (!medicine1.get().getManufacturingDate().equals(medicine.getManufacturingDate())){
                medicine1.get().setManufacturingDate(medicine.getManufacturingDate());
            }
            if (!medicine1.get().getExpirationDate().equals(medicine.getExpirationDate())){
                medicine1.get().setExpirationDate(medicine.getExpirationDate());
            }
            if (medicine1.get().getPrice() == medicine.getPrice()){
                medicine1.get().setPrice(medicine.getPrice());
            }
            logger.info("Medicine is fetched");
        } else {
            logger.error("No medicine found with this ID.");
            throw new MedicineNotFoundException("No medicine found for this ID.");
        }
        return medicineRepository.save(medicine1.get());
    }

    @Override
    public String deleteMedicineById(Long medicineId) {
        medicineRepository.deleteById(medicineId);
        logger.info("Medicine deleted successfully!");
        return "Deletion successful";
    }

    @Override
    public List<Medicine> searchMedicineByKeyword(String keyword) {
        logger.info("Found the searched medicine successfully!");
        return medicineRepository.findByMedicineNameContaining(keyword);
    }
}
