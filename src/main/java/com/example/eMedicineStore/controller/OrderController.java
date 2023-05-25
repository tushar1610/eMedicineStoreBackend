package com.example.eMedicineStore.controller;

import com.example.eMedicineStore.entity.Cart;
import com.example.eMedicineStore.entity.Order;
import com.example.eMedicineStore.exception.OrderNotFoundException;
import com.example.eMedicineStore.exception.OrderNotProcessedException;
import com.example.eMedicineStore.service.CartService;
import com.example.eMedicineStore.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class OrderController {

    public static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @GetMapping("/getOrderById/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) throws Exception {
        logger.info("Calling getOrderById method");
        Order order = orderService.getOrderById(orderId);
        if (order == null){
            logger.error("No order found for this exception");
            throw new OrderNotFoundException("No order found for this id.");
        }
        return order;
    }

    @PostMapping("/addOrder")
    public Order addOrder(@RequestBody Cart cart) throws Exception{
        logger.info("Calling getCartByUserId method");
        Optional<Cart> savedCart = cartService.getCartByUserId(cart.getUser().getUserId());
        Order savedOrder;
        if (savedCart.isPresent()){
            cartService.updateCartById(cart, savedCart.get().getCartId());
            savedOrder = orderService.addOrder(savedCart.get());
        } else {
            savedOrder = orderService.addOrder(cartService.addToCart(cart));
        }
        if (savedOrder == null) {
            logger.error("Order cannot be processed");
            throw new OrderNotProcessedException("Order cannot be processed. Try again later.");
        }
        cartService.deleteCartByUserUserId(savedOrder.getUser().getUserId());
        logger.info("Order is generated successfully!");
        return savedOrder;
    }

    @PutMapping("/cancelOrder/{orderId}")
    public String cancelOrder(@PathVariable Long orderId){
        logger.info("Calling cancelOrder method");
        orderService.cancelOrder(orderId);
        return "Order cancelled";
    }

}