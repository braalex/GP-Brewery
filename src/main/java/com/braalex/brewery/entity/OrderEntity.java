package com.braalex.brewery.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Table(name = "order")
@Entity
public class OrderEntity extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
    @ManyToOne(optional = false)
    @JoinColumn(name = "beer_id")
    private BeerEntity beer;
    private Integer quantity;
    private LocalDate orderDate;
}
