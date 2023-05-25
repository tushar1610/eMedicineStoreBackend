package com.example.eMedicineStore.serviceImpl;

import com.example.eMedicineStore.entity.*;
import com.example.eMedicineStore.repository.MedicineRepository;
import com.example.eMedicineStore.repository.OrderRepository;
import com.example.eMedicineStore.repository.UserRepository;
import com.example.eMedicineStore.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    public static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public Order getOrderById(Long orderId) {
        logger.info("Found order by ID successfully!");
        return orderRepository.findById(orderId).get();
    }

    @Override
    public Order addOrder(Cart cart) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem item = OrderItem.builder()
                    .medicine(cartItem.getMedicine())
                    .quantity(cartItem.getQuantity())
                    .build();
            orderItems.add(item);
        }
        Order order = Order.builder()
                .date(LocalDate.now().toString())
                .user(cart.getUser())
                .orderItems(orderItems)
                .status("Processing")
                .totalAmount(calculateTotalAmount(cart.getCartItems()))
                .build();
        logger.info("Saved order successfully!");
        return orderRepository.save(order);
    }

    private Double calculateTotalAmount(List<CartItem> cartItems) {
        Double total = 0.0;
        for (CartItem cartItem : cartItems) {
            total += (cartItem.getMedicine().getPrice()*cartItem.getQuantity());
        }
        return total;
    }

    @Override
    public void cancelOrder(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()){
            order.get().setStatus("Cancelled");
        }
        orderRepository.save(order.get());
        logger.info("Order is saved");
    }

}