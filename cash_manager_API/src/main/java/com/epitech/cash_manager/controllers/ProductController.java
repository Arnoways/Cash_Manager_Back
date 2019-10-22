package com.epitech.cash_manager.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @RequestMapping(value="/Produits", method=RequestMethod.GET)
    public String listeProduits(){
        return "un exemple de produit";
    }

    @GetMapping(value="/Produits/{id}")
    public String afficherUnProduit(@PathVariable int id) {
        return "Vous avez demandé un produit avec l'id  " + id;
    }



}
