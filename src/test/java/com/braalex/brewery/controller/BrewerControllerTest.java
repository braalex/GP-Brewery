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
public class BrewerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testBrewerSignUpIsCreated() throws Exception {
        // given
        // when
        mockMvc.perform(post("/brewers/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        " \"email\" : \"ivanov123@email.com\",\n" +
                        " \"password\" : \"ilovebeer\",\n" +
                        " \"firstName\" : \"Sergey\",\n" +
                        " \"lastName\" : \"Ivanov\",\n" +
                        " \"dateOfBirth\" : \"1982-06-11\" \n" +
                        "}"))
                // then
                .andExpect(status().isCreated())
                .andExpect(content().json("{\n" +
                        " \"id\" : 5\n" +
                        "}"));
    }

    @Test
    public void testBrewerSignInIsOk() throws Exception {
        // given
        // when
        mockMvc.perform(post("/brewers/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        " \"email\" : \"ivanov123@email.com\",\n" +
                        " \"password\" : \"ilovebeer\"\n" +
                        "}"))
                // then
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        " \"id\" : 5\n" +
                        "}"));
    }

    @Test
    public void testGetBrewListIsOk() throws Exception {
        // given
        // when
        mockMvc.perform(get("/brewers/5/brews"))
                // then
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"brewId\" : 1, \n" +
                        "    \"brewerId\" : 5, \n" +
                        "    \"beerId\" : 2, \n" +
                        "    \"startDate\" : \"2020-02-10\",\n" +
                        "    \"endDate\" : \"2020-03-25\" \n" +
                        "  }\n" +
                        "]"));
    }
}
