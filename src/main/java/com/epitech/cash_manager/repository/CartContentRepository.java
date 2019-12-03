package com.epitech.cash_manager.repository;

import com.epitech.cash_manager.models.CartContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartContentRepository extends JpaRepository<CartContent, Long> {
    List<CartContent> findByCartId(Long cartId);
    List<CartContent> findByIdAndCartId(Long id, Long cartId);
    List<CartContent> findByProductId(Long productId);
    List<CartContent> findByIdAndProductId(Long id, Long productId);
}

