package com.epitech.cash_manager.controllers;

import com.epitech.cash_manager.dto.UserRequestDto;
import com.epitech.cash_manager.dto.UserResponseDto;
import com.epitech.cash_manager.exception.ResourceNotFoundException;
import com.epitech.cash_manager.models.User;
import com.epitech.cash_manager.repository.UserRepository;
import com.epitech.cash_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping(value="/api/users")
    public Iterable<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    @PostMapping(value="/api/users")
    public User createUser (@Valid @RequestBody User user)
    {
        return userRepository.save(user);
    }

    @GetMapping(value="api/users/{id}")
    public User getUserById(@PathVariable(value = "id") Long userId)
    {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    @PostMapping(value = "/api/users/create")
    public UserResponseDto createUserWithCart(@RequestBody UserRequestDto user)
    {
        return userService.createUser(user);
    }

    @PutMapping(value = "/api/users/{id}")
    public User updateUser(@PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails)
    {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        user.setLogin(userDetails.getLogin());
        user.setEmail(userDetails.getEmail());
        user.setPwd(userDetails.getPwd());

        return userRepository.save(user);
    }

    @DeleteMapping(value = "/api/users/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long userId)
    {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

}
