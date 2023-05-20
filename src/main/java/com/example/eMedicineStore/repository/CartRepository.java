package com.example.eMedicineStore.repository;

import com.example.eMedicineStore.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByUserUserId(Long userId);

}
