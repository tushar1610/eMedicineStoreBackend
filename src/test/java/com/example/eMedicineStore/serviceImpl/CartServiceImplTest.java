package com.example.eMedicineStore.serviceImpl;

import com.example.eMedicineStore.entity.Cart;
import com.example.eMedicineStore.entity.CartItem;
import com.example.eMedicineStore.entity.Medicine;
import com.example.eMedicineStore.entity.User;
import com.example.eMedicineStore.exception.CartNotFoundException;
import com.example.eMedicineStore.repository.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
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
class CartServiceImplTest {

    @Mock
    private CartRepository cartRepository;

    @Autowired
    private CartServiceImpl cartService;

    @Test
    void addToCart() {
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

        when(cartRepository.save(cart)).thenReturn(cart);

        Cart createdCart = cartService.addToCart(cart);

        assertNotNull(createdCart);
    }

    @Test
    void updateCartById() throws CartNotFoundException {
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

        when(cartRepository.findById(cart.getCartId())).thenReturn(Optional.of(cart));
        Cart updatedCart = cartService.updateCartById(cart, 1L);


        assertNotNull(updatedCart);
    }

    @Test
    void deleteCartById() {

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

        when(cartRepository.findById(cart.getCartId())).thenReturn(Optional.of(cart));

        cartService.deleteCartById(cart.getCartId());


    }

    @Test
    void deleteCartByUserUserId() {

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

        when(cartRepository.findById(cart.getUser().getUserId())).thenReturn(Optional.of(cart));

        cartService.deleteCartById(cart.getUser().getUserId());

    }

    @Test
    void getCartByUserId() {

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

        when(cartRepository.findByUserUserId(cart.getUser().getUserId())).thenReturn(Optional.of(cart));

        Optional<Cart> foundCart = cartService.getCartByUserId(cart.getUser().getUserId());

        assertNotNull(foundCart);
    }
}

