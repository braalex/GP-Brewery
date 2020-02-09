package com.braalex.brewery.controller;

import com.braalex.brewery.dto.CustomerSignUpRequest;
import com.braalex.brewery.dto.IdResponse;
import com.braalex.brewery.dto.OrderDto;
import com.braalex.brewery.dto.UserSignInRequest;
import com.braalex.brewery.exception.SuchUserAlreadyExistException;
import com.braalex.brewery.service.CustomerService;
import com.braalex.brewery.service.OrderService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Log
@Data
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;

    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public IdResponse signUp(@RequestBody final CustomerSignUpRequest request)
            throws SuchUserAlreadyExistException {
        log.info("email = " + request.getEmail());
        log.info("category = " + request.getCategory());
        return customerService.signUp(request);
    }

    @PostMapping(value = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    public IdResponse signIn(@RequestBody final UserSignInRequest request) {
        log.info("email = " + request.getEmail());
        return customerService.signIn(request);
    }

    @PostMapping(value = "/1/orders", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public IdResponse createOrder(@RequestBody final OrderDto request) {
        log.info("Order date: " + request.getOrderDate());
        return orderService.createOrder(request);
    }

    @GetMapping(value = "/1/orders")
    public List<OrderDto> getList() {
        List<OrderDto> orderList = orderService.getList()
                .stream()
                .filter(order -> order.getCustomerId() == 1)
                .collect(Collectors.toList());
        log.info("Number of beers: " + orderList.size());
        log.info("Order date: " + orderList.get(0).getOrderDate());
        return orderList;
    }
}
