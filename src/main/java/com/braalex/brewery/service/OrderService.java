package com.braalex.brewery.service;

import com.braalex.brewery.dto.OrderDto;
import com.braalex.brewery.mapper.OrderMapper;
import com.braalex.brewery.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderDto> getOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::destinationToSource)
                .collect(Collectors.toList());
    }

    public List<OrderDto> getOrdersByCustomer(final Long id) {
        return orderRepository.findAllByCustomerId(id).stream()
                .map(orderMapper::destinationToSource)
                .collect(Collectors.toList());
    }

    public OrderDto createOrder(final Long id, final OrderDto request) {
        OrderDto order = OrderDto.builder()
                .customerId(id)
                .beerId(request.getBeerId())
                .quantity(request.getQuantity())
                .orderDate(request.getOrderDate())
                .build();
        orderRepository.save(orderMapper.sourceToDestination(order));
        return order;
    }
}
