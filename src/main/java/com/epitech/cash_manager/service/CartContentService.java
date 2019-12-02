package com.epitech.cash_manager.service;


import com.epitech.cash_manager.exception.ResourceNotFoundException;
import com.epitech.cash_manager.models.Cart;
import com.epitech.cash_manager.models.CartContent;
import com.epitech.cash_manager.models.Product;
import com.epitech.cash_manager.repository.CartContentRepository;
import com.epitech.cash_manager.repository.CartRepository;
import com.epitech.cash_manager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class CartContentService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartContentRepository cartContentRepository;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    private CartContent cartContent = new CartContent();


    public CartContent createCartContent()
    {
        cartContentRepository.save(cartContent);
        return cartContent;
    }

    public CartContent getCartContentById(Long cartContentId)
    {
        return cartContentRepository.findById(cartContentId).orElseThrow(() -> new ResourceNotFoundException("CartContent", "id", cartContentId));
    }










}
