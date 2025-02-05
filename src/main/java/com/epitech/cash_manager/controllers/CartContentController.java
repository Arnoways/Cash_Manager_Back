package com.epitech.cash_manager.controllers;

import com.epitech.cash_manager.exception.ResourceNotFoundException;
import com.epitech.cash_manager.models.CartContent;
import com.epitech.cash_manager.models.Product;
import com.epitech.cash_manager.repository.CartContentRepository;
import com.epitech.cash_manager.repository.CartRepository;
import com.epitech.cash_manager.service.CartContentService;
import com.epitech.cash_manager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CartContentController {

    @Autowired
    private CartContentRepository cartContentRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartContentService cartContentService;

    @GetMapping("api/cartContent/cart/{cartId}")
    public List<CartContent> getAllCartContentsByCartId(@PathVariable (value = "cartId") Long cartId) {
        return cartContentRepository.findByCartId(cartId);
    }

    @GetMapping("api/cartContent/product/{productId}")
    public List<CartContent> getAllCartContentsByProductId(@PathVariable (value = "productId") Long productId) {
        return cartContentRepository.findByProductId(productId);
    }

    @GetMapping("api/cartContent/{id}")
    public CartContent getCartContentById(@PathVariable (value = "id") Long cartContentId)
    {
        return cartContentService.getCartContentById(cartContentId);
    }


    @PostMapping("api/cartContent/{cartId}/{productId}/cartContents")
    public CartContent createCartContent(@PathVariable (value = "cartId") Long cartId,
                                         @PathVariable (value = "productId") Long productId,
                                 @Valid @RequestBody CartContent cartContent) {
        Product product = productService.getProductById(productId);
        return cartRepository.findById(cartId).map(cart -> {
            int qty = cartContent.getQuantity();
            double price = qty * product.getPrice();
            cart.setTotal(cart.getTotal() + price);
            cartContent.setCart(cart);
            cartContent.setProduct(product);
            return cartContentRepository.save(cartContent);
        }).orElseThrow(() -> new ResourceNotFoundException("Cart", "id", cartId));
    }


    @PutMapping("api/carts/{cartId}/cartContents/{cartContentId}")
    public CartContent updateCartContent(@PathVariable (value = "cartId") Long cartId,
                                 @PathVariable (value = "cartContentId") Long cartContentId,
                                 @Valid @RequestBody CartContent cartContentRequest) {
        if(!cartRepository.existsById(cartId)) {
            throw new ResourceNotFoundException("Cart", "id", cartId);
        }

        return cartContentRepository.findById(cartContentId).map(cartContent -> {
            cartContent.setQuantity(cartContentRequest.getQuantity());
            return cartContentRepository.save(cartContent);
        }).orElseThrow(() -> new ResourceNotFoundException("CartContent", "id", cartContentId));
    }

}
