package com.braalex.brewery.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Table(name = "order")
@Entity
public class OrderEntity extends BaseEntity {
    private Long customerId;
    private Long beerId;
    private Integer quantity;
    private LocalDate orderDate;
}
