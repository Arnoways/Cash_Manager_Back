package com.epitech.cash_manager.dao;


import com.epitech.cash_manager.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDAO extends CrudRepository<Product, Long> {
    public Product findByName(String name);
}
