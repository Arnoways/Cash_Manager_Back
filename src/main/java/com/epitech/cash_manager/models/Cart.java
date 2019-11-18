package com.epitech.cash_manager.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cart")
@JsonIgnoreProperties(allowGetters = true)

public class Cart implements Serializable {
    public Cart() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "cart")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cart")
    private Set<Product> product = new HashSet<>();

    @Value("0")
    private Long quantity;

    @Value("0")
    private Double total;


    public Long getId()
    {
        return this.id;
    }



    public Long getQuantity()
    {
        return this.quantity;
    }


    public Double getTotal()
    {
        return this.total;
    }

    public void setId(Long id)
    {
        this.id = id;
    }


    public void setQuantity(Long quantity)
    {
        this.quantity = quantity;
    }


    public void setTotal(Double total)
    {
        this.total = total;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Set<Product> getProduct() {
        return product;
    }

    public void setProduct(Set<Product> product) {
        this.product = product;
    }
}
