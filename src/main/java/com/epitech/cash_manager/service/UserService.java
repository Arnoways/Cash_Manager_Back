package com.epitech.cash_manager.service;


import com.epitech.cash_manager.controllers.UserController;
import com.epitech.cash_manager.dao.UserDAO;
import com.epitech.cash_manager.dto.UserRequestDto;
import com.epitech.cash_manager.dto.UserResponseDto;
import com.epitech.cash_manager.exception.ResourceNotFoundException;
import com.epitech.cash_manager.models.Cart;
import com.epitech.cash_manager.models.CartContent;
import com.epitech.cash_manager.models.User;
import com.epitech.cash_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    @Autowired
    CartContentService cartContentService;


    public UserResponseDto createUser(UserRequestDto in) {
        User user = null;
        if (in.getLogin().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Incorrect value for login");
        } else if (getUserByEmail(in.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This email is already used");
        } else {
            user = new User();
            user.setEmail(in.getEmail().toLowerCase());
            user.setLogin(in.getLogin());
            user.setPwd(in.getPwd());
            Cart cart = cartService.createCart();
            CartContent cartContent = cartContentService.createCartContent();
            cartContent.setCart(cart);
            user.setCart(cart);
            userRepository.save(user);
        }
        return new UserResponseDto(user.getId(), user.getEmail(), user.getLogin());
    }


    public User getUserByEmail(String email) {

        return userDAO.findByEmail(email);
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


    public Cart getCart(Long userId)
    {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userID",userId));
        return user.getCart();
    }

}