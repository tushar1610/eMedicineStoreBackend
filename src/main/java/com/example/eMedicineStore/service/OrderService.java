package com.example.eMedicineStore.service;

import com.example.eMedicineStore.entity.Order;
import com.example.eMedicineStore.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    Order getOrderById(Long orderId);

    Order addOrder(Order order);
}
