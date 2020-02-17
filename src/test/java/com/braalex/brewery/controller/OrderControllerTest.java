package com.braalex.brewery.controller;

import com.braalex.brewery.mock.OrderControllerMockData;
import com.braalex.brewery.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;

import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest extends AbstractControllerTest {

    @SpyBean
    private OrderService orderService;

    @Test
    public void testCustomerNewOrderIsCreated() throws Exception {
        // given
        final String token = signInAsCustomer();
        willReturn(OrderControllerMockData.getNewOrder())
                .given(orderService).createOrder(1L, OrderControllerMockData.postNewOrder());
        // when
        mockMvc.perform(post("/customers/1/orders").header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        " \"beerId\" : 1,\n" +
                        " \"quantity\" : 100,\n" +
                        " \"orderDate\" : \"2020-02-06\" \n" +
                        "}"))
                // then
                .andExpect(status().isCreated())
                .andExpect(content().json("{\n" +
                        " \"id\" : 15,\n" +
                        " \"customerId\" : 1,\n" +
                        " \"beerId\" : 1,\n" +
                        " \"quantity\" : 100,\n" +
                        " \"orderDate\" : \"2020-02-06\" \n" +
                        "}"));
    }

    @Test
    public void testCustomerGetOrderListIsOk() throws Exception {
        // given
        final String token = signInAsCustomer();
        willReturn(OrderControllerMockData.getOrderListByCustomer())
                .given(orderService).getOrdersByCustomerId(1L);
        // when
        mockMvc.perform(get("/customers/1/orders").header("Authorization", token))
                // then
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"id\" : 15, \n" +
                        "    \"customerId\" : 1, \n" +
                        "    \"beerId\" : 1, \n" +
                        "    \"quantity\" : 100,\n" +
                        "    \"orderDate\" : \"2020-02-06\" \n" +
                        "  }\n" +
                        "]"));
    }

    @Test
    public void testBrewerGetOrderListIsForbidden() throws Exception {
        // given
        final String token = signInAsBrewer();
        // when
        mockMvc.perform(get("/customers/1/orders").header("Authorization", token))
                // then
                .andExpect(status().isForbidden());
    }

    @Test
    public void testDirectorNewOrderIsForbidden() throws Exception {
        // given
        final String token = signInAsDirector();
        // when
        mockMvc.perform(post("/customers/1/orders").header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        " \"customerId\" : 1,\n" +
                        " \"beerId\" : 1,\n" +
                        " \"quantity\" : 100,\n" +
                        " \"orderDate\" : \"2020-02-06\" \n" +
                        "}"))
                // then
                .andExpect(status().isForbidden());
    }

    @Test
    public void testDirectorGetOrderListIsOk() throws Exception {
        // given
        final String token = signInAsDirector();
        willReturn(OrderControllerMockData.getOrderList())
                .given(orderService).getOrders();
        // when
        mockMvc.perform(get("/director/orders").header("Authorization", token))
                // then
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"id\" : 15, \n" +
                        "    \"customerId\" : 1, \n" +
                        "    \"beerId\" : 1, \n" +
                        "    \"quantity\" : 100,\n" +
                        "    \"orderDate\" : \"2020-02-06\" \n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\" : 16, \n" +
                        "    \"customerId\" : 4, \n" +
                        "    \"beerId\" : 2, \n" +
                        "    \"quantity\" : 150,\n" +
                        "    \"orderDate\" : \"2020-02-07\" \n" +
                        "  }\n" +
                        "]"));
    }
}
