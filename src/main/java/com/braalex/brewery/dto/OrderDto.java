package com.braalex.brewery.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class OrderDto {
    private Long id;
    private Long customerId;
    private Long beerId;
    private Integer quantity;
    private LocalDate orderDate;
}
