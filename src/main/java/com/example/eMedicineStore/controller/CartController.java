package com.example.eMedicineStore.controller;

import com.example.eMedicineStore.entity.Cart;
import com.example.eMedicineStore.entity.User;
import com.example.eMedicineStore.service.CartService;
import com.example.eMedicineStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/getCartById/{cartId}")
    public Cart getCartById(@PathVariable Long cartId){
        return cartService.getCartById(cartId);
    }

    @PostMapping("/addToCart")
    public Cart addToCart(@RequestBody Cart cart){
        return cartService.addToCart(cart);
    }

    @PutMapping("/updateCartById/{cartId}")
    public Cart updateCartById(@PathVariable Long cartId, @RequestBody Cart cart){
        return cartService.updateCartById(cart, cartId);
    }
    @DeleteMapping("/deleteCartById/{cartId}")
    public String deleteCartById(@PathVariable Long cartId){
        return cartService.deleteCartById(cartId);
    }

}
