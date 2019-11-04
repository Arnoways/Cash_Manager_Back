package eu.epitech.cashmanager.controllers;
import eu.epitech.cashmanager.exception.ResourceNotFoundException;
import eu.epitech.cashmanager.models.Cart;
import eu.epitech.cashmanager.models.User;
import eu.epitech.cashmanager.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CartController{
    @Autowired
    CartRepository cartRepository;

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
        cart.setTotal_without_taxes(cartDetails.getTotal_without_taxes());
        cart.setTotal_taxes(cartDetails.getTotal_taxes());
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

}
