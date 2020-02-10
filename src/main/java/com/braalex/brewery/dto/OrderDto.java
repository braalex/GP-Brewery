package com.braalex.brewery.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class OrderDto {
    private long id;
    private long customerId;
    private long beerId;
    private int quantity;
    private LocalDate orderDate;
}
