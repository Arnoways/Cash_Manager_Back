package com.epitech.cash_manager.controllers;

import com.epitech.cash_manager.exception.ResourceNotFoundException;
import com.epitech.cash_manager.models.Product;
import com.epitech.cash_manager.repository.CartRepository;
import com.epitech.cash_manager.repository.ProductRepository;
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

    @RequestMapping(method = RequestMethod.GET, value = "/api/product/all")
    public Iterable<Product> getAllProduct()
    {
        return productService.getAllProducts();
    }

    @GetMapping("/api/carts/{cartId}/products")
    public Page<Product> getAllProductByCartId(@PathVariable (value = "cartId") Long cartId,
                                                Pageable pageable) {
        return productRepository.findByCartId(cartId, pageable);
    }

    @GetMapping(value = "/api/products")
    public Iterable<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    @PostMapping("/api/carts/{cartId}/product")
    public Product createProductWithCartId(@PathVariable (value = "cartId") Long cartId,
                                 @Valid @RequestBody Product product) {
        return cartRepository.findById(cartId).map(cart -> {
            product.setCart(cart);
            return productRepository.save(product);
        }).orElseThrow(() -> new ResourceNotFoundException("Cart", "id", cartId));
    }

    @PostMapping(value="/api/product")
    public Product createProduct(@Valid @RequestBody Product product)
    {
        return productRepository.save(product);
    }

    @GetMapping(value="/api/product/{id}")
    public Product getProductById(@PathVariable(value="id") Long productId)
    {
        return productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
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
    public ResponseEntity<?> deleteProduct(@PathVariable(value="id") Long productId)
    {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        productRepository.delete(product);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/product/{name}")
    @ResponseBody
    public Product getProductByName(@PathVariable(value = "name") String name)
    {
        return productService.getProductByName(name);
    }
}
