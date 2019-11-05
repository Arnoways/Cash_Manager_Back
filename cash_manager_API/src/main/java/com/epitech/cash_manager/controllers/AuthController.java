package com.epitech.cash_manager.controllers;

import com.epitech.cash_manager.dto.AuthResponseDto;
import com.epitech.cash_manager.dto.LoginRequestDto;
import com.epitech.cash_manager.dto.RegisterRequestDto;
import com.epitech.cash_manager.service.AuthService;
import com.epitech.cash_manager.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    TokenService tokenService;

    @PostMapping(value = "/api/signUp")
    public AuthResponseDto signUp(@RequestBody RegisterRequestDto in) {
        return authService.signUp(in);
    }

    @PostMapping(value = "/api/signIn")
    public AuthResponseDto signIn(@RequestBody LoginRequestDto in) {
        return authService.signIn(in);
    }

    @PostMapping(value = "/api/signOut/{userID}")
    public void signOut(@PathVariable(value = "userID") Long userID) {
        authService.signOut(userID);
    }

}