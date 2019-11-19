package com.epitech.cash_manager.controllers;

import com.epitech.cash_manager.exception.ResourceNotFoundException;
import com.epitech.cash_manager.models.Product;
import com.epitech.cash_manager.repository.CartRepository;
import com.epitech.cash_manager.repository.ProductRepository;
import com.epitech.cash_manager.service.CartService;
import com.epitech.cash_manager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

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


    @GetMapping()
    @ResponseBody
    public Product getProductByName(@PathVariable(value = "name") String name)
    {
        return productService.getProductByName(name);
    }


    @GetMapping("/api/carts/{cartId}/products")
    public Page<Product> getAllProductByCartId(@PathVariable (value = "cartId") Long cartId,
                                                Pageable pageable) {
        return productRepository.findByCartId(cartId, pageable);
    }



    @PostMapping("/api/carts/{cartId}/{productId}")
    public Product createProductWithCartId(@PathVariable (value = "cartId") Long cartId,
                                           @PathVariable (value = "productId") Long productId) {
        Product product = productService.getProductById(productId);
        return cartRepository.findById(cartId).map(cart -> {
            product.setCart(cart);
            cartService.updateCart(cart,product);
            return productRepository.save(product);
        }).orElseThrow(() -> new ResourceNotFoundException("Cart", "id", cartId));
    }


    @PutMapping("/carts/{cartId}/products/{productId}")
    public Product updateProductWithCartId(@PathVariable (value = "cartId") Long cartId,
                                 @PathVariable (value = "productId") Long productId,
                                 @Valid @RequestBody Product productRequest) {
        if(!cartRepository.existsById(cartId)) {
            throw new ResourceNotFoundException("Cart", "id", cartId);
        }

        return productRepository.findById(productId).map(product -> {
            product.setName(productRequest.getName());
            product.setImage(productRequest.getImage());
            product.setPrice(productRequest.getPrice());
            return productRepository.save(product);
        }).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
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

    @DeleteMapping("/api/carts/{cartId}/products/{productId}")
    public ResponseEntity<?> deleteProductWithCartId(@PathVariable (value = "cartId") Long cartId,
                                           @PathVariable (value = "productId") Long productId) {
        return productRepository.findByIdAndCartId(productId, cartId).map(product -> {
            productRepository.delete(product);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
    }

    @DeleteMapping("/api/product/{id}")
    public Product deleteProduct(@PathVariable(value="id") Long productId)
    {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        productRepository.delete(product);

        return product;
    }
}
