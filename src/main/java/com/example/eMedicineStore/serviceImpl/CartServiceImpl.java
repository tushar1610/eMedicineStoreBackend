package com.example.eMedicineStore.serviceImpl;

import com.example.eMedicineStore.entity.Cart;
import com.example.eMedicineStore.entity.CartItem;
import com.example.eMedicineStore.entity.Medicine;
import com.example.eMedicineStore.entity.User;
import com.example.eMedicineStore.repository.CartRepository;
import com.example.eMedicineStore.repository.MedicineRepository;
import com.example.eMedicineStore.repository.UserRepository;
import com.example.eMedicineStore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MedicineRepository medicineRepository;
    
    @Override
    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId).get();
    }

    @Override
    public Cart addToCart(Cart cart) {
        Optional<User> user = userRepository.findById(cart.getUser().getUserId());

        if (user.isPresent()){
            cart.setUser(user.get());
        }

        List<CartItem> cartItemList = cart.getCartItems();
        for (int i = 0; i < cartItemList.size(); i++) {
            Optional<Medicine> medicine = medicineRepository.findById(cartItemList.get(i).getMedicine().getMedicineId());

            if (medicine.isPresent()){
                cartItemList.get(i).setMedicine(medicine.get());
            }
        }
        return cartRepository.save(cart);
    }

    @Override
    public Cart updateCartById(Cart cart, Long cartId) {
        Optional<Cart> cart1 = cartRepository.findById(cartId);
        if (cart1.isPresent()){
            //update code
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
        Cart cart = cartRepository.findByUserUserId(userId);
        cartRepository.deleteById(cart.getCartId());
    }
}
