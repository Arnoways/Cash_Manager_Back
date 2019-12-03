package com.epitech.cash_manager.controllers;

import com.epitech.cash_manager.exception.ResourceNotFoundException;
import com.epitech.cash_manager.models.CartContent;
import com.epitech.cash_manager.models.Product;
import com.epitech.cash_manager.repository.CartContentRepository;
import com.epitech.cash_manager.repository.CartRepository;
import com.epitech.cash_manager.repository.ProductRepository;
import com.epitech.cash_manager.service.CartService;
import com.epitech.cash_manager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductService productService;

    @Autowired
    CartService cartService;

    @Autowired
    CartContentRepository cartContentRepository;



    @PostMapping(value="/api/products")
    public Product createProduct(@Valid @RequestBody Product product)
    {
        return productService.createProduct(product);
    }


    @GetMapping(value = "/api/products/all")
    public Iterable<Product> getAllProduct()
    {
        return productService.getAllProducts();
    }


    @GetMapping(value="/api/products/{id}")
    public Product getProductById(@PathVariable(value="id") Long productId)
    {
        return productService.getProductById(productId);
    }


    @GetMapping(value="/api/products/name/{name}")
    public Product getProductByName(@PathVariable(value = "name") String name)
    {
        return productService.getProductByName(name);
    }


    @PutMapping("/api/product/{id}")
    public Product updateProduct(@PathVariable(value = "id") Long productId,@Valid @RequestBody Product productDetails)
    {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        product.setName(productDetails.getName());
        product.setImage(productDetails.getImage());
        product.setPrice(productDetails.getPrice());

        return productRepository.save(product);
    }

    @GetMapping(value = "/api/products/cartContent/{cartId}")
    public Iterable<Product> getAllProductsByCartId(@PathVariable (value = "cartId") Long cartId)
    {
        List<Product> product = new ArrayList<>();
        List<CartContent> cartContent = cartContentRepository.findByCartId(cartId);
        for (CartContent c : cartContent)
        {
            product.add(c.getProduct());
        }
        return product;
    }

    @DeleteMapping("/api/product/{id}")
    public void deleteProduct(@PathVariable(value="id") Long productId)
    {
        List<CartContent> cartContent = cartContentRepository.findByProductId(productId);
        for (CartContent c : cartContent)
        {
            c.getCart().setTotal(c.getCart().getTotal() - c.getProduct().getPrice());
            productRepository.delete(c.getProduct());
        }
    }

    @DeleteMapping("/api/product/cartContent/{cartContentId}")
    public void deleteProductInCartContentWithCartContentId(@PathVariable(value="cartContentId") Long cartContentId)
    {
        CartContent cartContent = cartContentRepository.findById(cartContentId).orElseThrow(() -> new ResourceNotFoundException("CartContent", "id", cartContentId));
        cartContent.getCart().setTotal(cartContent.getCart().getTotal() - cartContent.getProduct().getPrice());
        cartContent.setProduct(null);
        cartContent.setCart(null);
        cartContentRepository.delete(cartContent);
    }

    @DeleteMapping("/api/product/cart/{cartId}")
    public void deleteProductInCartContentWithCartId(@PathVariable(value="cartId") Long cartId)
    {
        List<CartContent> cartContent = cartContentRepository.findByCartId(cartId);
        for (CartContent c : cartContent)
        {
            c.getCart().setTotal(c.getCart().getTotal() - c.getProduct().getPrice());
            c.setProduct(null);
            c.setCart(null);
            cartContentRepository.delete(c);
        }
    }

}
