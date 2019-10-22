package com.epitech.cash_manager.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "cart")
@JsonIgnoreProperties(allowGetters = true)

public class Cart implements Serializable {
    public Cart() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private User user;

    @NotBlank
    private Product product;

    @NotBlank
    private int quantity;

    @NotBlank
    private Double total_without_taxes;

    @NotBlank
    private Double total_taxes;

    @NotBlank
    private Double total;


    public Long getId()
    {
        return this.id;
    }

    public User getUser()
    {
        return this.user;
    }

    public Product getProduct()
    {
        return this.product;
    }

    public int getQuantity()
    {
        return this.quantity;
    }

    public Double getTotal_without_taxes()
    {
        return this.total_without_taxes;
    }

    public Double getTotal_taxes()
    {
        return this.total_taxes;
    }

    public Double getTotal()
    {
        return this.total;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public void setTotal_without_taxes(Double total_without_taxes)
    {
        this.total_without_taxes = total_without_taxes;
    }

    public void setTotal_taxes(Double total_taxes)
    {
        this.total_taxes = total_taxes;
    }

    public void setTotal(Double total)
    {
        this.total = total;
    }




}
