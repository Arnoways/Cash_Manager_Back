package com.epitech.cash_manager.controllers;

import com.epitech.cash_manager.exception.ResourceNotFoundException;
import com.epitech.cash_manager.models.Cart;
import com.epitech.cash_manager.models.CartContent;
import com.epitech.cash_manager.models.User;
import com.epitech.cash_manager.repository.CartContentRepository;
import com.epitech.cash_manager.repository.CartRepository;
import com.epitech.cash_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
public class PaymentController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartContentRepository cartContentRepository;

    @Autowired
    CartRepository cartRepository;

    @PostMapping(value="/api/payment/{userId}")
    public Map<String, String> payment(@PathVariable (value = "userId") Long userId)
    {

        HashMap<String, String> map = new HashMap<>();
        Random rd = new Random();
        String payment_status = (rd.nextBoolean()) ? "Authorized" : "Refused";
        map.put("payment_status", payment_status);
        if (map.get("payment_status") == "Authorized")
        {
            User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
            Cart cart = user.getCart();
            Long cartId = cart.getId();
            List<CartContent> cartContent = cartContentRepository.findByCartId(cartId);
            for (CartContent c : cartContent) {
                c.setProduct(null);
                c.setCart(null);
                cartContentRepository.delete(c);
            }
            cart.setTotal(0.0);
            cartRepository.save(cart);
        }
        return map;
    }
}
