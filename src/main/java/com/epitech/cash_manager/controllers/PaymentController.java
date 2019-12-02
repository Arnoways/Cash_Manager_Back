package com.epitech.cash_manager.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
