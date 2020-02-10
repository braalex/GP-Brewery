package com.braalex.brewery.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCustomerSignUpIsCreated() throws Exception {
        // given
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
                .andExpect(content().json("{\n" +
                        " \"id\" : 1,\n" +
                        " \"email\" : \"craft-bar@email.com\",\n" +
                        " \"category\" : \"bar\",\n" +
                        " \"companyName\" : \"Craft Bar\" \n" +
                        "}"));
    }

    @Test
    public void testCustomerSignInIsOk() throws Exception {
        // given
        // when
        mockMvc.perform(post("/customers/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        " \"email\" : \"craft-bar@email.com\",\n" +
                        " \"password\" : \"qwerty\"\n" +
                        "}"))
                // then
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        " \"id\" : 1,\n" +
                        " \"email\" : \"craft-bar@email.com\"\n" +
                        "}"));
    }

    @Test
    public void testCustomerNewOrderIsCreated() throws Exception {
        // given
        // when
        mockMvc.perform(post("/customers/1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        " \"customerId\" : 1,\n" +
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
        // when
        mockMvc.perform(get("/customers/1/orders"))
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
}
