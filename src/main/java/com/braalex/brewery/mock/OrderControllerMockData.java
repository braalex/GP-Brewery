package com.braalex.brewery.mock;

import com.braalex.brewery.dto.OrderDto;

import java.time.LocalDate;
import java.util.List;

public class OrderControllerMockData {

    public static OrderDto getNewOrder() {
        return OrderDto.builder()
                .id(15L)
                .customerId(1L)
                .beerId(1L)
                .quantity(100)
                .orderDate(LocalDate.of(2020, 2, 6))
                .build();
    }

    public static OrderDto postNewOrder() {
        return OrderDto.builder()
                .beerId(1L)
                .quantity(100)
                .orderDate(LocalDate.of(2020, 2, 6))
                .build();
    }

    public static List<OrderDto> getOrderListByCustomer() {
        return List.of(OrderDto.builder()
                .id(15L)
                .customerId(1L)
                .beerId(1L)
                .quantity(100)
                .orderDate(LocalDate.of(2020, 2, 6))
                .build());
    }

    public static List<OrderDto> getOrderList() {
        return List.of(OrderDto.builder()
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
    }
}
