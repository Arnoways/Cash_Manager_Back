package com.epitech.cash_manager.service;

import com.epitech.cash_manager.exception.ResourceNotFoundException;
import com.epitech.cash_manager.models.Cart;
import com.epitech.cash_manager.models.Product;
import com.epitech.cash_manager.repository.CartRepository;
import com.epitech.cash_manager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    private Cart cart = new Cart();


    public Cart createCart()
    {
        cartRepository.save(cart);
        return cart;
    }

    public Iterable<Cart> getCart(Long userId)
    {
        return cartRepository.findAll();
    }

    public void updateCart(Cart cart,Product product)
    {

        cart.setTotal(cart.getTotal()+product.getPrice());
    }


    public Cart getCartById(Long cartId)
    {
        return cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart", "id", cartId));
    }



}
