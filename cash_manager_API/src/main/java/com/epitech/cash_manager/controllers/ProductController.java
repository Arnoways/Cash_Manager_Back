package com.epitech.cash_manager.controllers;

import com.epitech.cash_manager.exception.ResourceNotFoundException;
import com.epitech.cash_manager.models.Product;
import com.epitech.cash_manager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping(value = "/api/product")
    public Iterable<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    @PostMapping(value="/api/product")
    public Product createProduct(@Valid @RequestBody Product product)
    {
        return productRepository.save(product);
    }

    @GetMapping(value="api/product/{id}")
    public Product getProductById(@PathVariable(value="id") Long productId)
    {
        return productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
    }

    @PutMapping("/api/product/{id}")
    public Product updateProduct(@PathVariable(value = "id") Long productId,@Valid @RequestBody Product productDetails)
    {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        product.setName(productDetails.getName());
        product.setImage(productDetails.getImage());
        product.setPrice(productDetails.getPrice());
        product.setPrice_without_taxes(productDetails.getPrice_without_taxes());

        Product updatedProduct = productRepository.save(product);
        return updatedProduct;
    }

    @DeleteMapping("/api/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value="id") Long productId)
    {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        productRepository.delete(product);

        return ResponseEntity.ok().build();
    }
}
