package eu.epitech.cashmanager.controllers;

import eu.epitech.cashmanager.exception.ResourceNotFoundException;
import eu.epitech.cashmanager.models.User;
import eu.epitech.cashmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

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

    @PutMapping(value = "/api/users/{id}")
    public User updateUser(@PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails)
    {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        user.setLogin(userDetails.getLogin());
        user.setPassword(userDetails.getPassword());
        user.setStatus(userDetails.getStatus());

        User updatedUser = userRepository.save(user);
        return updatedUser;
    }

    @DeleteMapping(value = "/api/users/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long userId)
    {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

}
