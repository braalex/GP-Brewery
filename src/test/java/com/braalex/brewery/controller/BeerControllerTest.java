package com.braalex.brewery.controller;

import com.braalex.brewery.dto.BeerDto;
import com.braalex.brewery.dto.IngredientDto;
import com.braalex.brewery.dto.IngredientType;
import com.braalex.brewery.service.BeerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BeerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private BeerService beerService;

    @Test
    public void testGetBeerListIsOk() throws Exception {
        // given
        willReturn(List.of(BeerDto.builder()
                        .id(1L)
                        .type("Stout")
                        .beerName("Espresso Stout")
                        .abv(6.1)
                        .originalGravity(14.0)
                        .description("Coffee stout")
                        .ingredients(List.of(IngredientDto.builder()
                                        .type(IngredientType.HOPS)
                                        .name("Magnum")
                                        .build(),
                                IngredientDto.builder()
                                        .type(IngredientType.MALT)
                                        .name("Brown Malt")
                                        .build(),
                                IngredientDto.builder()
                                        .type(IngredientType.YEAST)
                                        .name("Ale Yeast")
                                        .build()))
                        .price(4.2)
                        .build(),
                BeerDto.builder()
                        .id(2L)
                        .type("IPA")
                        .beerName("Madness")
                        .abv(6.6)
                        .originalGravity(13.0)
                        .description("American style IPA")
                        .ingredients(List.of(IngredientDto.builder()
                                        .type(IngredientType.HOPS)
                                        .name("Cascade")
                                        .build(),
                                IngredientDto.builder()
                                        .type(IngredientType.MALT)
                                        .name("Rye Malt")
                                        .build(),
                                IngredientDto.builder()
                                        .type(IngredientType.YEAST)
                                        .name("Yeast")
                                        .build()))
                        .price(5.3)
                        .build())).given(beerService).getBeers();
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
}
