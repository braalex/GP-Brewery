package com.braalex.brewery.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BeerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetBeerListIsOk() throws Exception {
        // given
        // when
        mockMvc.perform(get("/beers"))
                // then
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                                "  {\n" +
                                "    \"beerId\" : 1, \n" +
                                "    \"type\" : \"Stout\",\n" +
                                "    \"beerName\" : \"Espresso Stout\",\n" +
                                "    \"abv\" : 6.1,\n" +
                                "    \"originalGravity\" : 14.0,\n" +
                                "    \"description\" : \"Coffee stout\",\n" +
                                "    \"ingredients\" : [\n" +
                                "          {\"type\" : \"HOPS\", \"name\" : \"Magnum\"},\n" +
                                "          {\"type\" : \"MALT\", \"name\" : \"Brown Malt\"},\n" +
                                "          {\"type\" : \"YEAST\", \"name\" : \"Ale Yeast\"}\n" +
                                "    ],\n" +
                                "    \"price\" : 4.2 \n" +
                                "  },\n" +
                                "  {\n" +
                                "    \"beerId\" : 2, \n" +
                                "    \"type\" : \"IPA\",\n" +
                                "    \"beerName\" : \"Madness\",\n" +
                                "    \"abv\" : 6.6,\n" +
                                "    \"originalGravity\" : 13.0,\n" +
                                "    \"description\" : \"American style IPA\",\n" +
                                "    \"ingredients\" : [\n" +
                                "          {\"type\" : \"HOPS\", \"name\" : \"Cascade\"},\n" +
                                "          {\"type\" : \"MALT\", \"name\" : \"Rye Malt\"},\n" +
                                "          {\"type\" : \"YEAST\", \"name\" : \"Yeast\"}\n" +
                                "    ],\n" +
                                "    \"price\" : 5.3 \n" +
                                "  }\n" +
                                "]"));
                    }
                }