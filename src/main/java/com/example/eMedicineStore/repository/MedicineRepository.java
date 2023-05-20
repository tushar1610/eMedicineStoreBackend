package com.example.eMedicineStore.repository;

import com.example.eMedicineStore.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    List<Medicine> findByMedicineNameContaining(String keyword);

}
