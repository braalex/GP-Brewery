package com.braalex.brewery.service;

import com.braalex.brewery.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final List<OrderDto> orders = new ArrayList<>();

    public List<OrderDto> getOrders() {
        return orders;
    }

    public List<OrderDto> getOrdersByCustomer(final Long id) {
        return orders.stream()
                .filter(order -> order.getCustomerId().equals(id))
                .collect(Collectors.toList());
    }

    public OrderDto createOrder(final Long id, final OrderDto request) {
        return null;
//                OrderDto.builder()
//                .id(15L)
//                .customerId(id)
//                .beerId(request.getBeerId())
//                .quantity(request.getQuantity())
//                .orderDate(request.getOrderDate())
//                .build();
    }
}
