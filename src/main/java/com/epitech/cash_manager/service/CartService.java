package com.epitech.cash_manager.service;

import com.epitech.cash_manager.exception.ResourceNotFoundException;
import com.epitech.cash_manager.models.Cart;
import com.epitech.cash_manager.models.Product;
import com.epitech.cash_manager.repository.CartRepository;
import com.epitech.cash_manager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

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

    Cart cart = new Cart();
    public Set<Product> getAllProduct()
    {
        return cart.getProduct();
    }

    public Cart createCart()
    {
        cartRepository.save(cart);
        return cart;
    }

    public Iterable<Cart> getCart(Long userId)
    {
        return cartRepository.findAll();
    }

    public boolean addToCart(Long userId, Long productId)
    {
        Cart cart = userService.getCart(userId);
        Product product = productService.getProductById(productId);
        Set<Product> products = cart.getProduct();
        products.add(product);
        cartRepository.save(cart);
        return true;

    }

    public Product addProduct(Product product)
    {
        cart.getProduct().add(product);
        return product;
    }

    public void deleteProduct(Long productId)
    {
        Product p = productService.getProductById(productId);
        cart.getProduct().remove(p);
    }

    public Cart getCartById(Long cartId)
    {
        return cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart", "id", cartId));
    }



}
