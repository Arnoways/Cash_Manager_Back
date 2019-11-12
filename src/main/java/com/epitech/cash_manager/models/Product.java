package com.epitech.cash_manager.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "product")
@JsonIgnoreProperties(allowGetters = true)

public class Product implements Serializable {

    public Product() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;


    private String name;

    private String image;

    private Double price;

    private Double price_without_taxes;


    public Long getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public String getImage()
    {
        return this.image;
    }

    public Double getPrice()
    {
        return this.price;
    }

    public Double getPrice_without_taxes()
    {
        return this.price_without_taxes;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public void setPrice_without_taxes(Double price_without_taxes)
    {
        this.price_without_taxes = price_without_taxes;
    }
}
