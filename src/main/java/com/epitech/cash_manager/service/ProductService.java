package com.epitech.cash_manager.service;


import com.epitech.cash_manager.dao.ProductDAO;
import com.epitech.cash_manager.exception.ResourceNotFoundException;
import com.epitech.cash_manager.models.Product;
import com.epitech.cash_manager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductDAO productDAO;

    public Product getProductById(Long productID) {
        return productRepository.findById(productID)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productID", productID));
    }

    public Product getProductByName(String name) {
        return productDAO.findByName(name);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

}