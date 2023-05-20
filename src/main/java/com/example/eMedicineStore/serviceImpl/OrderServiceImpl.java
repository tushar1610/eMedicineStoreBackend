package com.example.eMedicineStore.serviceImpl;

import com.example.eMedicineStore.entity.*;
import com.example.eMedicineStore.repository.MedicineRepository;
import com.example.eMedicineStore.repository.OrderRepository;
import com.example.eMedicineStore.repository.UserRepository;
import com.example.eMedicineStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).get();
    }

    @Override
    public Order addOrder(Order order) {
        Optional<User> user = userRepository.findById(order.getUser().getUserId());
        if (user.isPresent()){
            order.setUser(user.get());
        }
        List<OrderItem> orderItemList = order.getOrderItems();
        for (int i = 0; i < orderItemList.size(); i++) {
            Optional<Medicine> medicine = medicineRepository.findById(orderItemList.get(i).getMedicine().getMedicineId());

            if (medicine.isPresent()){
                orderItemList.get(i).setMedicine(medicine.get());
            }
        }
        order.setDate(LocalDate.now().toString());
        return orderRepository.save(order);
    }

    @Override
    public void cancelOrder(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()){
            order.get().setStatus("Cancelled");
        }
        orderRepository.save(order.get());
    }

}
