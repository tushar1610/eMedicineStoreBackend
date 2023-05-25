package com.example.eMedicineStore.controller;

import com.example.eMedicineStore.entity.Cart;
import com.example.eMedicineStore.entity.User;
import com.example.eMedicineStore.exception.CartNotAddedException;
import com.example.eMedicineStore.exception.CartNotFoundException;
import com.example.eMedicineStore.exception.CartNotUpdatedException;
import com.example.eMedicineStore.service.CartService;
import com.example.eMedicineStore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {

    public static final Logger logger = LoggerFactory.getLogger(CartController.class);
    @Autowired
    private CartService cartService;

    @GetMapping("/getCartById/{cartId}")
    public Cart getCartById(@PathVariable Long cartId) throws Exception{
        logger.info("Calling getCartById method");
        Cart cart = cartService.getCartById(cartId);
        if (cart == null){
            logger.error("Cart not found for this ID");
            throw new CartNotFoundException("Cart not found for this ID.");
        }
        return cart;
    }

    @PostMapping("/addToCart")
    public Cart addToCart(@RequestBody Cart cart) throws Exception {
        logger.info("Calling addTocart method");
        Cart cart1 = cartService.addToCart(cart);
        if (cart1 == null){
            logger.error("Cart cannot be added");
            throw new CartNotAddedException("Cart cannot be added");
        }
        return cart1;
    }

    @PutMapping("/updateCartById/{cartId}")
    public Cart updateCartById(@PathVariable Long cartId, @RequestBody Cart cart) throws Exception{
        logger.info("Calling updateCartById method");
        Cart cart1 = cartService.updateCartById(cart, cartId);
        if (cart1 == null){
            throw new CartNotUpdatedException("Cart cannot be updated. Try again later.");
        }
        return cart1;
    }
    @DeleteMapping("/deleteCartById/{cartId}")
    public String deleteCartById(@PathVariable Long cartId){
        logger.info("Calling deleteCartById method");
        return cartService.deleteCartById(cartId);
    }

}
