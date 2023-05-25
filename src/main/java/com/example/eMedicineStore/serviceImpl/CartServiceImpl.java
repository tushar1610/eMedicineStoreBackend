package com.example.eMedicineStore.serviceImpl;

import com.example.eMedicineStore.entity.Cart;
import com.example.eMedicineStore.entity.CartItem;
import com.example.eMedicineStore.entity.Medicine;
import com.example.eMedicineStore.entity.User;
import com.example.eMedicineStore.exception.CartNotFoundException;
import com.example.eMedicineStore.repository.CartItemRepository;
import com.example.eMedicineStore.repository.CartRepository;
import com.example.eMedicineStore.repository.MedicineRepository;
import com.example.eMedicineStore.repository.UserRepository;
import com.example.eMedicineStore.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    public static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Override
    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId).get();
    }

    @Override
    public Cart addToCart(Cart cart) {
        Optional<User> user = userRepository.findById(cart.getUser().getUserId());

        if (user.isPresent()){
            cart.setUser(user.get());
            logger.info("User is fetched.");
        }

        List<CartItem> cartItemList = cart.getCartItems();
        for (CartItem cartItem : cartItemList) {
            Optional<Medicine> medicine = medicineRepository.findById(cartItem.getMedicine().getMedicineId());

            if (medicine.isPresent()){
                cartItem.setMedicine(medicine.get());
                cartItem.setAmount(cartItem.getMedicine().getPrice()*cartItem.getQuantity());
                logger.info("If cart is found, medicine price is being calculated.");
            }
        }
        return cartRepository.save(cart);
    }

    @Transactional
    @Override
    public Cart updateCartById(Cart cart, Long cartId) throws CartNotFoundException {
        Optional<Cart> cart1 = cartRepository.findById(cartId);
        if (cart1.isPresent()){
            //update code
            List<CartItem> existingCartItems = cart1.get().getCartItems();
            List<CartItem> updatedCartItems = cart.getCartItems();
            List<CartItem> cartItemsToUpdate = existingCartItems.stream()
                    .filter(existingCartItem -> updatedCartItems.stream()
                            .anyMatch(updatedCartItem -> updatedCartItem.getCartItemId().equals(existingCartItem.getCartItemId())))
                    .collect(Collectors.toList());
            List<CartItem> cartItemsToAdd = updatedCartItems.stream()
                    .filter(updatedCartItem -> existingCartItems.stream()
                            .noneMatch(existingCartItem -> existingCartItem.getCartItemId().equals(updatedCartItem.getCartItemId())))
                    .collect(Collectors.toList());
            List<CartItem> cartItemsToRemove = existingCartItems.stream()
                    .filter(existingCartItem -> updatedCartItems.stream()
                            .noneMatch(updatedCartItem -> updatedCartItem.getCartItemId().equals(existingCartItem.getCartItemId())))
                    .collect(Collectors.toList());
            for(CartItem cartItem : cartItemsToUpdate){
                Optional<CartItem> updatedCartItemOptional = updatedCartItems.stream()
                        .filter(updatedCartItem -> updatedCartItem.getCartItemId().equals(cartItem.getCartItemId()))
                        .findFirst();
                if (updatedCartItemOptional.isPresent()){
                    cartItem.setQuantity(updatedCartItemOptional.get().getQuantity());
                }
            }
            List<CartItem> cartItems = cartItemRepository.saveAll(cartItemsToUpdate);
            cartItems.addAll(cartItemsToAdd);
            for (CartItem cartItem : cartItemsToRemove){
                cartItemRepository.delete(cartItem);
            }
            cart1.get().setCartItems(cartItems);
            logger.info("Cart items have been updated.");
        } else {
            logger.error("No cart for this ID");
            throw new CartNotFoundException("No cart found for this ID");
        }
        return cartRepository.save(cart1.get());
    }

    @Override
    public String deleteCartById(Long cartId) {
        cartRepository.deleteById(cartId);
        return "Deletion successful";
    }

    @Override
    public void deleteCartByUserUserId(Long userId) {
        Cart cart = cartRepository.findByUserUserId(userId).get();
        cartRepository.deleteById(cart.getCartId());
        logger.info("Medicine deleted successfully in cart!");
    }

    @Override
    public Optional<Cart> getCartByUserId(Long userId) {
        logger.info("Found user successful!");
        return cartRepository.findByUserUserId(userId);
    }
}
