package com.epitech.cash_manager.service;


import com.epitech.cash_manager.controllers.UserController;
import com.epitech.cash_manager.dao.UserDAO;
import com.epitech.cash_manager.dto.UserRequestDto;
import com.epitech.cash_manager.dto.UserResponseDto;
import com.epitech.cash_manager.exception.ResourceNotFoundException;
import com.epitech.cash_manager.models.Cart;
import com.epitech.cash_manager.models.User;
import com.epitech.cash_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserController userController;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    CartService cartService;

    /*@Autowired
    private PasswordEncoder passwordEncoder;*/


    public User getUserByEmail(String email) {

        return userDAO.findByEmail(email);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }


    public UserResponseDto createUser(UserRequestDto in) {
        User user = null;
        Long userID = null;
        if (in.getLogin().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Incorrect value for login");
        } else if (getUserByEmail(in.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This email is already used");
        } else {
            user = new User();
            userID = UUID.randomUUID().timestamp();
            user.setId(userID);
            user.setEmail(in.getEmail().toLowerCase());
            user.setLogin(in.getLogin());
            //user.setPwd(passwordEncoder.encode(in.getPwd()));
            user.setPwd(in.getPwd());
            Cart cart = cartService.createCart();
            user.setCart(cart);
            userRepository.save(user);
        }
        return new UserResponseDto(user.getId(), user.getEmail(), user.getLogin());
    }



    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void updateUser(Long userID, UserRequestDto up) {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userID", userID));

        user.setLogin(up.getLogin());
        user.setEmail(up.getEmail());
        user.setPwd(up.getPwd());

        userRepository.save(user);
    }

    public void updateUser(User user) {
        //userRepository.save(user);
    }

    public User getUserById(Long userID) {
        return userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userID", userID));
    }

    public void deleteUser(Long userID) {
        User user = userRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User", "userID",userID));
        userRepository.delete(user);
    }

    public Cart getCart(Long userId)
    {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userID",userId));
        return user.getCart();
    }

}