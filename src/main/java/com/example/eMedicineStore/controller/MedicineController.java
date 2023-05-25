package com.example.eMedicineStore.controller;

import com.example.eMedicineStore.entity.Medicine;
import com.example.eMedicineStore.exception.MedicineNotCreatedException;
import com.example.eMedicineStore.exception.MedicineNotFoundException;
import com.example.eMedicineStore.service.MedicineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicineController {
    public static final Logger logger = LoggerFactory.getLogger(MedicineController.class);

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/getMedicineById/{medicineId}")
    public Medicine getMedicineById(@PathVariable Long medicineId) throws Exception {
        logger.info("Calling getMedicineById method");
        Medicine medicine = medicineService.getMedicineById(medicineId);
        if (medicine == null){
            throw new MedicineNotFoundException("No medicine found for this id.");
        }
        logger.info("Medicine Found successfully!");
        return medicine;
    }

    @PostMapping("/addMedicine")
    public Medicine addMedicine(@RequestBody Medicine medicine) throws Exception{
        logger.info("Calling addMedicine method");
        Medicine medicine1 = medicineService.addMedicine(medicine);
        if (medicine == null){
            throw new MedicineNotCreatedException("Medicine cannot be created. Try again later.");
        }
        logger.info("Medicine added successfully!");
        return medicine1;
    }

    @PutMapping("/updateMedicineById/{medicineId}")
    public Medicine updateMedicineById(@PathVariable Long medicineId, @RequestBody Medicine medicine) throws MedicineNotFoundException {
        logger.info("Calling updateMedicineById method");
        return  medicineService.updateMedicineById(medicine, medicineId);
    }
    @DeleteMapping("/deleteMedicineById/{medicineId}")
    public String deleteMedicineById(@PathVariable Long medicineId){
        logger.info("Calling deleteMedicineId method");
        return medicineService.deleteMedicineById(medicineId);
    }

    @GetMapping("/searchMedicineByKeyword")
    public List<Medicine> searchMedicineByKeyword(@RequestParam("keyword") String keyword){
        logger.info("Calling searchMedicineByKeyword method");
        return medicineService.searchMedicineByKeyword(keyword);
    }
}
