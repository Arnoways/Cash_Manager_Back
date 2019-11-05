package com.epitech.cash_manager.dao;


import com.epitech.cash_manager.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User, Long> {
    public User findByEmail(String email);
}
