package com.epitech.cash_manager.controllers;
import com.epitech.cash_manager.exception.ResourceNotFoundException;
import com.epitech.cash_manager.models.Cart;
import com.epitech.cash_manager.models.Product;
import com.epitech.cash_manager.models.User;
import com.epitech.cash_manager.repository.CartRepository;
import com.epitech.cash_manager.service.CartService;
import com.epitech.cash_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CartController{
    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;



    @GetMapping(value ="/api/carts")
    public Iterable<Cart> getAllCarts()
    {
        return cartRepository.findAll();
    }


    @PostMapping(value = "/api/{userId}/carts")
    public Cart createCart(@PathVariable (value = "userId") Long userId,
                           @Valid @RequestBody Cart cart)
    {
        User user = userService.getUserById(userId);
        user.setCart(cart);
        cart.setUser(user);
        return cartRepository.save(cart);
    }


    @GetMapping(value = "/api/carts/{id}")
    public Cart getCartById(@PathVariable(value="id") Long cartId)
    {
        return cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart", "id", cartId));
    }



    @PutMapping(value = "/api/carts/{id}")
    public Cart updateCart(@PathVariable(value = "id") Long cartId, @Valid @RequestBody Cart cartDetails)
    {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart", "id", cartId));
        cart.setUser(cartDetails.getUser());
        cart.setTotal(cartDetails.getTotal());
        cart.setTotal(cartDetails.getTotal());
        Cart updatedCart = cartRepository.save(cart);
        return updatedCart;
    }

    @DeleteMapping("/api/cart/{id}")
    public Cart deleteCart(@PathVariable(value="id") Long cartId)
    {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart", "id", cartId));

        cart.getUser().setCart(null);
        cart.setUser(null);
        cartRepository.delete(cart);

        return cart;
    }


}
