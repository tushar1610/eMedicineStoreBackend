package com.example.eMedicineStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;
    @OneToOne
    @JoinColumn(name = "medicineId", referencedColumnName = "medicineId")
    private Medicine medicine;
    private Integer quantity;
    private Double amount;

}
