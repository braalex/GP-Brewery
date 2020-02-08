package com.braalex.brewery.service;

import com.braalex.brewery.dto.IdResponse;
import com.braalex.brewery.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private final List<OrderDto> orders = List.of(OrderDto.builder()
                    .orderId(15L)
                    .customerId(1L)
                    .beerId(1L)
                    .quantity(100)
                    .orderDate(LocalDate.of(2020, 2, 6))
                    .build(),
            OrderDto.builder()
                    .orderId(16L)
                    .customerId(4L)
                    .beerId(2L)
                    .quantity(150)
                    .orderDate(LocalDate.of(2020, 2, 7))
                    .build());

    public List<OrderDto> getList() {
        return orders;
    }

    public IdResponse createOrder(final OrderDto request) {
        return new IdResponse(15);
    }
}
