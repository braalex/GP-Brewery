package com.braalex.brewery.controller;

import com.braalex.brewery.dto.*;
import com.braalex.brewery.exception.SuchUserAlreadyExistException;
import com.braalex.brewery.service.CustomerService;
import com.braalex.brewery.service.OrderService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@Data
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;

    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserSignInResponseDto signUp(@RequestBody final CustomerDto request)
            throws SuchUserAlreadyExistException {
        log.info("email = " + request.getEmail());
        return customerService.signUp(request);
    }

    @PostMapping(value = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserSignInResponseDto signIn(@RequestBody final UserSignInRequestDto request) {
        log.info("email = " + request.getEmail());
        return customerService.signIn(request);
    }

    @PostMapping(value = "/{id}/orders", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@PathVariable final Long id, @RequestBody final OrderDto request) {
        log.info("Order date: " + request.getOrderDate());
        return orderService.createOrder(id, request);
    }

    @GetMapping(value = "/{id}/orders")
    public List<OrderDto> getOrders(@PathVariable final Long id) {
        List<OrderDto> orderList = orderService.getOrdersByCustomer(id);
        log.info("Number of beers: " + orderList.size());
        log.info("Order date: " + orderList.get(0).getOrderDate());
        return orderList;
    }
}
