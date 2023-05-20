package com.example.eMedicineStore.controller;

import com.example.eMedicineStore.entity.Order;
import com.example.eMedicineStore.service.CartService;
import com.example.eMedicineStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @GetMapping("/getOrderById/{orderId}")
    public Order getOrderById(@PathVariable Long orderId){
        return orderService.getOrderById(orderId);
    }

    @PostMapping("/addOrder")
    public Order addOrder(@RequestBody Order order){
        Order savedOrder = orderService.addOrder(order);
        if (savedOrder != null){
            cartService.deleteCartByUserUserId(savedOrder.getUser().getUserId());
            return savedOrder;
        }
        return null;
    }

    @PutMapping("/cancelOrder/{orderId}")
    public String cancelOrder(@PathVariable Long orderId){
        orderService.cancelOrder(orderId);
        return "Order cancelled";
    }

}
