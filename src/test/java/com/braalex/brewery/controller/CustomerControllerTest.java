package com.braalex.brewery.controller;

import com.braalex.brewery.mock.OrderControllerMockData;
import com.braalex.brewery.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;

import java.util.Optional;

import static org.hamcrest.Matchers.hasLength;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CustomerControllerTest extends AbstractControllerTest {

    @SpyBean
    private OrderService orderService;

    @Test
    public void testCustomerSignUpIsCreated() throws Exception {
        // given
        willReturn(Optional.empty(), Optional.of(createCustomerInfo()))
                .given(userRepository).findByEmail("craft-bar@email.com");
        // when
        mockMvc.perform(post("/customers/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        " \"email\" : \"craft-bar@email.com\",\n" +
                        " \"password\" : \"qwerty\",\n" +
                        " \"category\" : \"bar\",\n" +
                        " \"companyName\" : \"Craft Bar\" \n" +
                        "}"))
                // then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("token", hasLength(149)));
    }

    @Test
    public void testCustomerSignUpWhenUserAlreadyExisted() throws Exception {
        // given
        signInAsCustomer();
        // when
        mockMvc.perform(post("/customers/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        " \"email\" : \"craft-bar@email.com\",\n" +
                        " \"password\" : \"qwerty\",\n" +
                        " \"category\" : \"bar\",\n" +
                        " \"companyName\" : \"Craft Bar\" \n" +
                        "}"))
                // then
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCustomerSignInIsOk() throws Exception {
        // given
        signInAsCustomer();
        // when
        mockMvc.perform(post("/customers/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        " \"email\" : \"craft-bar@email.com\",\n" +
                        " \"password\" : \"qwerty\"\n" +
                        "}"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("token", hasLength(149)));
    }

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
                .given(orderService).getOrdersByCustomer(1L);
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
}
