package com.example.eMedicineStore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicineId;
    @NotBlank(message = "Medicine name cannot be empty.")
    @Column(unique = true)
    private String medicineName;
    @NotBlank(message = "Description cannot be empty.")
    @Size(min = 20, max = 100)
    private String description;
    private Double price;
    private LocalDate manufacturingDate; // "manufacturingDate" : "yyyy-mm-dd"
    private LocalDate expirationDate; // "expirationDate" : "yyyy-mm-dd"

}
