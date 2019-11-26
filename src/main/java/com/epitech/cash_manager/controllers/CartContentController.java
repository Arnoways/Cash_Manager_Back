package com.epitech.cash_manager.controllers;

import com.epitech.cash_manager.exception.ResourceNotFoundException;
import com.epitech.cash_manager.models.CartContent;
import com.epitech.cash_manager.repository.CartContentRepository;
import com.epitech.cash_manager.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CartContentController {

    @Autowired
    private CartContentRepository cartContentRepository;

    @Autowired
    private CartRepository cartRepository;

    @GetMapping("api/carts/{cartId}/cartContents")
    public Page<CartContent> getAllCartContentsByCartId(@PathVariable (value = "cartId") Long cartId,
                                                Pageable pageable) {
        return cartContentRepository.findByCartId(cartId, pageable);
    }

    @PostMapping("api/carts/{cartId}/cartContents")
    public CartContent createCartContent(@PathVariable (value = "cartId") Long cartId,
                                 @Valid @RequestBody CartContent cartContent) {
        return cartRepository.findById(cartId).map(cart -> {
            cartContent.setCart(cart);
            return cartContentRepository.save(cartContent);
        }).orElseThrow(() -> new ResourceNotFoundException("Cart", "id", cartId));
    }

    @PutMapping("api/carts/{cartId}/cartContents/{cartContentId}")
    public CartContent updateCartContent(@PathVariable (value = "cartId") Long cartId,
                                 @PathVariable (value = "cartContentId") Long cartContentId,
                                 @Valid @RequestBody CartContent cartContent) {
        if(!cartRepository.existsById(cartId)) {
            throw new ResourceNotFoundException("Cart", "id", cartId);
        }

        return cartContentRepository.findById(cartContentId).map(comment -> {
            cartContent.setQuantity(cartContent.getQuantity());
            return cartContentRepository.save(cartContent);
        }).orElseThrow(() -> new ResourceNotFoundException("CartContent", "id", cartContentId));
    }

    @DeleteMapping("api/carts/{cartId}/cartContents/{cartContentId}")
    public ResponseEntity<?> deleteCartContent(@PathVariable (value = "cartId") Long cartId,
                                           @PathVariable (value = "cartContentId") Long cartContentId) {
        return cartContentRepository.findByIdAndCartId(cartContentId, cartId).map(cartContent -> {
            cartContentRepository.delete(cartContent);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("CartContent", "id", cartContentId));
    }
}
