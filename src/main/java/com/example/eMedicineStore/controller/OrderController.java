package com.example.eMedicineStore.controller;

import com.example.eMedicineStore.entity.Order;
import com.example.eMedicineStore.entity.User;
import com.example.eMedicineStore.service.OrderService;
import com.example.eMedicineStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/getOrderById/{orderId}")
    public Order getOrderById(@PathVariable Long orderId){
        return orderService.getOrderById(orderId);
    }

    @PostMapping("/addOrder")
    public Order addOrder(@RequestBody Order order){
        return orderService.addOrder(order);
    }

}
