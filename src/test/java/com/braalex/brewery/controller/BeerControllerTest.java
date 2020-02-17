package com.braalex.brewery.controller;

import com.braalex.brewery.mapper.BeerMapper;
import com.braalex.brewery.mock.BeerControllerMockData;
import com.braalex.brewery.repository.BeerRepository;
import com.braalex.brewery.service.BeerService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;

import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class BeerControllerTest extends AbstractControllerTest {

    @SpyBean
    private BeerService beerService;
    @MockBean
    private BeerRepository beerRepository;
    @MockBean
    private BeerMapper beerMapper;

    @Test
    public void testGetBeerListIsOk() throws Exception {
        // given
        willReturn(BeerControllerMockData.getBeerList()).given(beerService).getBeers();
        // when
        mockMvc.perform(get("/beers"))
                // then
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"id\" : 1, \n" +
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
                        "    \"id\" : 2, \n" +
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

    @Test
    public void testDirectorDeleteBeerIsOk() throws Exception {
        // given
        final String token = signInAsDirector();
        beerRepository.save(beerMapper.sourceToDestination(BeerControllerMockData.getBeer()));
        // when
        mockMvc.perform(delete("/director/beers/1").header("Authorization", token))
                // then
                .andExpect(status().isNoContent());
    }

    @Test
    public void testCustomerDeleteBeerIsForbidden() throws Exception {
        // given
        final String token = signInAsCustomer();
        // when
        mockMvc.perform(delete("/director/beers/1").header("Authorization", token))
                // then
                .andExpect(status().isForbidden());
    }

    @Test
    public void testDirectorNewBeerIsCreated() throws Exception {
        // given
        final String token = signInAsDirector();
        willReturn(BeerControllerMockData.getNewBeer())
                .given(beerService).createBeer(BeerControllerMockData.postNewBeer());
        // when
        mockMvc.perform(post("/director/beers").header("Authorization", token)
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
                        "    \"id\" : 3,\n" +
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
                        "}"));
    }
}
