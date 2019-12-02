package com.epitech.cash_manager.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
        this.total = 0.0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonManagedReference
    @OneToOne(mappedBy = "cart", targetEntity = User.class)
    private User user;



    private Double total;


    public Long getId()
    {
        return this.id;
    }




    public Double getTotal()
    {
        return this.total;
    }

    public void setId(Long id)
    {
        this.id = id;
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
}
