package com.example.eMedicineStore.serviceImpl;

import com.example.eMedicineStore.entity.Medicine;
import com.example.eMedicineStore.repository.MedicineRepository;
import com.example.eMedicineStore.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public Medicine getMedicineById(Long medicineId) {
        return medicineRepository.findById(medicineId).get();
    }

    @Override
    public Medicine addMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    @Override
    public Medicine updateMedicineById(Medicine medicine, Long medicineId) {
        Optional<Medicine> medicine1 = medicineRepository.findById(medicineId);
        if (medicine1.isPresent()){
            //update code
        }
        return medicineRepository.save(medicine1.get());
    }

    @Override
    public String deleteMedicineById(Long medicineId) {
        medicineRepository.deleteById(medicineId);
        return "Deletion successful";
    }
}
