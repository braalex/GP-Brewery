package com.braalex.brewery.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
        mockMvc.perform(post("/customer/sign-up")
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
                        "  \"customerId\" : 1\n" +
                        "}"));
    }

    @Test
    public void testCustomerSignInIsOk() throws Exception {
        // given
        // when
        mockMvc.perform(post("/customer/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"craft-bar@email.com\",\n" +
                        "  \"password\" : \"qwerty\"\n" +
                        "}"))
                // then
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"customerId\" : 1\n" +
                        "}"));
    }
}
