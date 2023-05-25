package com.example.eMedicineStore.service;

import com.example.eMedicineStore.entity.Cart;
import com.example.eMedicineStore.exception.CartNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CartService {
    Cart getCartById(Long cartId);

    Cart addToCart(Cart cart);

    Cart updateCartById(Cart cart, Long cartId) throws CartNotFoundException;

    String deleteCartById(Long cartId);

    void deleteCartByUserUserId(Long userId);

    Optional<Cart> getCartByUserId(Long userId);
}
