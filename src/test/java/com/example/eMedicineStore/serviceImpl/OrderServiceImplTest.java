package com.example.eMedicineStore.serviceImpl;

import com.example.eMedicineStore.entity.*;
import com.example.eMedicineStore.repository.CartRepository;
import com.example.eMedicineStore.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Autowired
    private OrderServiceImpl orderService;


    @Test
    void getOrderById() {

        User user = User.builder()
                .userId(1L)
                .userName("user")
                .phoneNum("1234567890")
                .password("password")
                .email("email@gmai.com")
                .build();

        Medicine medicine = Medicine.builder()
                .medicineId(1L)
                .medicineName("Medicine 1")
                .description("Description")
                .price(20.00)
                .manufacturingDate(LocalDate.now())
                .expirationDate(LocalDate.now()).build();

        OrderItem orderItem = OrderItem.builder()
                .orderItemId(1L)
                .medicine(medicine)
                .quantity(20)
                .build();

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);

        Order order = Order.builder()
                .orderId(1L)
                .date(LocalDate.now().toString())
                .status("Processing")
                .user(user)
                .orderItems(orderItems)
                .totalAmount(20*medicine.getPrice())
                .build();

        when(orderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));

        Order fetchedOrder = orderService.getOrderById(1L);

        assertNotNull(fetchedOrder);

    }

    @Test
    void addOrder() {

        User user = User.builder()
                .userId(1L)
                .userName("user")
                .phoneNum("1234567890")
                .password("password")
                .email("email@gmai.com")
                .build();

        Medicine medicine = Medicine.builder()
                .medicineId(1L)
                .medicineName("Medicine 1")
                .description("Description")
                .price(20.00)
                .manufacturingDate(LocalDate.now())
                .expirationDate(LocalDate.now()).build();

        CartItem cartItem = CartItem.builder()
                .cartItemId(1L)
                .medicine(medicine)
                .quantity(20)
                .amount(20*medicine.getPrice())
                .build();
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);
        Cart cart = Cart.builder()
                .cartId(1L)
                .cartItems(cartItems)
                .user(user)
                .build();

        OrderItem orderItem = OrderItem.builder()
                .orderItemId(1L)
                .medicine(medicine)
                .quantity(cartItem.getQuantity())
                .build();

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);

        Order order = Order.builder()
                .orderId(1L)
                .date(LocalDate.now().toString())
                .status("Processing")
                .user(user)
                .orderItems(orderItems)
                .totalAmount(20*cartItem.getMedicine().getPrice())
                .build();

        when(orderRepository.save(order)).thenReturn(order);

        Order savedOrder = orderService.addOrder(cart);

        assertNotNull(savedOrder);
    }

}