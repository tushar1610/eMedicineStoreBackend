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
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderItemId;
    @OneToOne
    @JoinColumn(name = "order_medicine_id", referencedColumnName = "medicineId")
    private Medicine medicine;
    private Integer quantity;
    private Double amount;

}
