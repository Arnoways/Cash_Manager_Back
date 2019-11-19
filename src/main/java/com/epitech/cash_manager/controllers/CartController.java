package com.epitech.cash_manager.controllers;
import com.epitech.cash_manager.exception.ResourceNotFoundException;
import com.epitech.cash_manager.models.Cart;
import com.epitech.cash_manager.repository.CartRepository;
import com.epitech.cash_manager.service.CartService;
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



    @GetMapping(value ="/api/carts")
    public Iterable<Cart> getAllCarts()
    {
        return cartRepository.findAll();
    }


    @PostMapping(value = "/api/carts")
    public Cart createCart(@Valid @RequestBody Cart cart)
    {
        return cartRepository.save(cart);
    }

    //@PostMapping(value = "/api/carts/{userId}/{productId}")
    //public boolean addProductToCart(@PathVariable(value = "userId") Long userId,
                                    //@PathVariable(value = "productId") Long productId)
    //{
        //return cartService.addToCart(userId,productId);
    //}


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
        cart.setProduct(cartDetails.getProduct());
        cart.setQuantity(cartDetails.getQuantity());
        cart.setTotal(cartDetails.getTotal());
        cart.setTotal(cartDetails.getTotal());
        Cart updatedCart = cartRepository.save(cart);
        return updatedCart;
    }

    @DeleteMapping(value = "/api/carts/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long cartId)
    {
        Cart cart= cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart", "id", cartId));
        cartRepository.delete(cart);
        return ResponseEntity.ok().build();

    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/carts/{userId}/all")
    public Iterable<Cart> getAllProducts(@PathVariable(value="userId") Long userId)
    {
        return cartService.getCart(userId);
    }




}
