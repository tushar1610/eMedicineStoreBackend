package com.example.eMedicineStore.service;

import com.example.eMedicineStore.entity.Medicine;
import com.example.eMedicineStore.exception.MedicineNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicineService {
    Medicine getMedicineById(Long medicineId);

    Medicine addMedicine(Medicine medicine);

    Medicine updateMedicineById(Medicine medicine, Long medicineId) throws MedicineNotFoundException;

    String deleteMedicineById(Long medicineId);

    List<Medicine> searchMedicineByKeyword(String keyword);
}
