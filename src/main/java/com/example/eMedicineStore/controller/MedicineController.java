package com.example.eMedicineStore.controller;

import com.example.eMedicineStore.entity.Medicine;
import com.example.eMedicineStore.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/getMedicineById/{medicineId}")
    public Medicine getMedicineById(@PathVariable Long medicineId){
        return medicineService.getMedicineById(medicineId);
    }

    @PostMapping("/addMedicine")
    public Medicine addMedicine(@RequestBody Medicine medicine){
        return medicineService.addMedicine(medicine);
    }

    @PutMapping("/updateMedicineById/{medicineId}")
    public Medicine updateMedicineById(@PathVariable Long medicineId, @RequestBody Medicine medicine){
        return  medicineService.updateMedicineById(medicine, medicineId);
    }
    @DeleteMapping("/deleteMedicineById/{medicineId}")
    public String deleteMedicineById(@PathVariable Long medicineId){
        return medicineService.deleteMedicineById(medicineId);
    }

    @GetMapping("/searchMedicineByKeyword")
    public List<Medicine> searchMedicineByKeyword(@RequestParam("keyword") String keyword){
        return medicineService.searchMedicineByKeyword(keyword);
    }
}
