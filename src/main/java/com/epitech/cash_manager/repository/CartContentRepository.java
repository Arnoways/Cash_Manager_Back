package com.epitech.cash_manager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.epitech.cash_manager.models.CartContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartContentRepository extends JpaRepository<CartContent, Long> {
    Page<CartContent> findByCartId(Long cartId, Pageable pageable);
    Optional<CartContent> findByIdAndCartId(Long id, Long cartId);
}

