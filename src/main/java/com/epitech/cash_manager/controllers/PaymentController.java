package com.epitech.cash_manager.controllers;

import com.epitech.cash_manager.exception.ResourceNotFoundException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;
import java.util.Random;
import java.util.Map;

@RestController
public class PaymentController {

    @PostMapping(value="/api/payment")
    public Map<String, String> payment() {
        HashMap<String, String> map = new HashMap<>();
        Random rd = new Random();
        String payment_status = (rd.nextBoolean()) ? "Authorized" : "Refused";
        map.put("payment_status", payment_status);
        return map;
    }
}