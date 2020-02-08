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
public class DirectorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testDirectorSignInIsOk() throws Exception {
        // given
        // when
        mockMvc.perform(post("/director/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        " \"email\" : \"bigboss@email.com\",\n" +
                        " \"password\" : \"secretpass\"\n" +
                        "}"))
                // then
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        " \"id\" : 1\n" +
                        "}"));
    }

    @Test
    public void testDirectorGetOrderListIsOk() throws Exception {
        // given
        // when
        mockMvc.perform(get("/director/orders"))
                // then
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"orderId\" : 15, \n" +
                        "    \"customerId\" : 1, \n" +
                        "    \"beerId\" : 1, \n" +
                        "    \"quantity\" : 100,\n" +
                        "    \"orderDate\" : \"2020-02-06\" \n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"orderId\" : 16, \n" +
                        "    \"customerId\" : 4, \n" +
                        "    \"beerId\" : 2, \n" +
                        "    \"quantity\" : 150,\n" +
                        "    \"orderDate\" : \"2020-02-07\" \n" +
                        "  }\n" +
                        "]"));
    }

    @Test
    public void testDirectorGetBrewListIsOk() throws Exception {
        // given
        // when
        mockMvc.perform(get("/director/brews"))
                // then
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"brewId\" : 1, \n" +
                        "    \"brewerId\" : 5, \n" +
                        "    \"beerId\" : 2, \n" +
                        "    \"startDate\" : \"2020-02-10\",\n" +
                        "    \"endDate\" : \"2020-03-25\" \n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"brewId\" : 2, \n" +
                        "    \"brewerId\" : 3, \n" +
                        "    \"beerId\" : 1, \n" +
                        "    \"startDate\" : \"2020-02-20\",\n" +
                        "    \"endDate\" : \"2020-04-20\" \n" +
                        "  }\n" +
                        "]"));
    }

    @Test
    public void testDirectorNewBeerIsCreated() throws Exception {
        // given
        // when
        mockMvc.perform(post("/director/beers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"type\" : \"Wheat\",\n" +
                        "    \"beerName\" : \"Summer\",\n" +
                        "    \"abv\" : 4.5,\n" +
                        "    \"originalGravity\" : 9.0,\n" +
                        "    \"description\" : \"Belgian style wheat beer\",\n" +
                        "    \"ingredients\" : [\n" +
                        "           {\"type\" : \"HOPS\", \"name\" : \"Zatec\"},\n" +
                        "           {\"type\" : \"MALT\", \"name\" : \"Wheat Malt\"},\n" +
                        "           {\"type\" : \"YEAST\", \"name\" : \"Yeast\"}\n" +
                        "    ],\n" +
                        "    \"price\" : 3.5 \n" +
                        "}"))
                // then
                .andExpect(status().isCreated())
                .andExpect(content().json("{\n" +
                        " \"id\" : 3\n" +
                        "}"));
    }

    @Test
    public void testDirectorNewBrewIsCreated() throws Exception {
        // given
        // when
        mockMvc.perform(post("/director/brews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"brewerId\" : 5, \n" +
                        "    \"beerId\" : 3, \n" +
                        "    \"startDate\" : \"2020-03-05\",\n" +
                        "    \"endDate\" : \"2020-05-12\" \n" +
                        "}"))
                // then
                .andExpect(status().isCreated())
                .andExpect(content().json("{\n" +
                        " \"id\" : 3\n" +
                        "}"));
    }
}
