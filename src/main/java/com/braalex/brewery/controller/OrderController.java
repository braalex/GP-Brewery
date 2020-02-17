package com.braalex.brewery.controller;

import com.braalex.brewery.dto.OrderDto;
import com.braalex.brewery.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;

    @GetMapping(value = "/director/orders")
    public List<OrderDto> getOrders() {
        return orderService.getOrders();
    }

    @PostMapping(value = "/customers/{id}/orders", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@PathVariable final Long id, @RequestBody final OrderDto orderDtoRequest) {
        return orderService.createOrder(id, orderDtoRequest);
    }

    @GetMapping(value = "/customers/{id}/orders")
    public List<OrderDto> getOrdersByCustomerId(@PathVariable final Long id) {
        return orderService.getOrdersByCustomerId(id);
    }
}
