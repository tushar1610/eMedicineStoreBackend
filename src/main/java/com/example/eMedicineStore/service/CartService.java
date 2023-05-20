package com.example.eMedicineStore.service;

import com.example.eMedicineStore.entity.Cart;
import com.example.eMedicineStore.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    Cart getCartById(Long cartId);

    Cart addToCart(Cart cart);

    Cart updateCartById(Cart cart, Long cartId);

    String deleteCartById(Long cartId);
}
