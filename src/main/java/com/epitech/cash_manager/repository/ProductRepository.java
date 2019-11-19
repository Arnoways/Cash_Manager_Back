package com.epitech.cash_manager.repository;

import com.epitech.cash_manager.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCartId(Long cartId, Pageable pageable);
    Optional<Product> findByIdAndCartId(Long id, Long cartId);
}