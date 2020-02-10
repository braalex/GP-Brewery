package com.braalex.brewery.service;

import com.braalex.brewery.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final List<OrderDto> orders = List.of(OrderDto.builder()
                    .id(15L)
                    .customerId(1L)
                    .beerId(1L)
                    .quantity(100)
                    .orderDate(LocalDate.of(2020, 2, 6))
                    .build(),
            OrderDto.builder()
                    .id(16L)
                    .customerId(4L)
                    .beerId(2L)
                    .quantity(150)
                    .orderDate(LocalDate.of(2020, 2, 7))
                    .build());

    public List<OrderDto> getOrders() {
        return orders;
    }

    public List<OrderDto> getOrdersByCustomer(final long id) {
        return orders.stream()
                .filter(order -> order.getCustomerId() == id)
                .collect(Collectors.toList());
    }

    public OrderDto createOrder(final long id, final OrderDto request) {
        return OrderDto.builder()
                .id(15L)
                .customerId(id)
                .beerId(request.getBeerId())
                .quantity(request.getQuantity())
                .orderDate(request.getOrderDate())
                .build();
    }
}
