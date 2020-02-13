package com.braalex.brewery.controller;

import com.braalex.brewery.dto.*;
import com.braalex.brewery.service.BeerService;
import com.braalex.brewery.service.BrewService;
import com.braalex.brewery.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.hasLength;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DirectorControllerTest extends AbstractControllerTest {

    @SpyBean
    private BrewService brewService;
    @SpyBean
    private OrderService orderService;
    @SpyBean
    private BeerService beerService;

    @Test
    public void testDirectorSignInIsOk() throws Exception {
        // given
        final User user = new User("bigboss@email.com",
                passwordEncoder.encode("secretpass"),
                List.of(new SimpleGrantedAuthority("DIRECTOR")));
        willReturn(user).given(loadUserDetailService).loadUserByUsername("bigboss@email.com");
        // when
        mockMvc.perform(post("/director/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        " \"email\" : \"bigboss@email.com\",\n" +
                        " \"password\" : \"secretpass\"\n" +
                        "}"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("token", hasLength(147)));
    }

    @Test
    public void testDirectorSignInWithWrongPassword() throws Exception {
        // given
        final User user = new User("bigboss@email.com",
                passwordEncoder.encode("secretpass"),
                List.of(new SimpleGrantedAuthority("DIRECTOR")));
        willReturn(user).given(loadUserDetailService).loadUserByUsername("bigboss@email.com");
        // when
        mockMvc.perform(post("/director/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        " \"email\" : \"bigboss@email.com\",\n" +
                        " \"password\" : \"wrong\"\n" +
                        "}"))
                // then
                .andExpect(status().isForbidden());
    }

    @Test
    public void testDirectorGetOrderListIsOk() throws Exception {
        // given
        final String token = signInAsDirector();
        willReturn(List.of(OrderDto.builder()
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
                        .build()))
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

    @Test
    public void testDirectorGetBrewListIsOk() throws Exception {
        // given
        final String token = signInAsDirector();
        willReturn(List.of(BrewDto.builder()
                        .id(1L)
                        .brewerId(5L)
                        .beerId(2L)
                        .startDate(LocalDate.of(2020, 2, 10))
                        .endDate(LocalDate.of(2020, 3, 25))
                        .build(),
                BrewDto.builder()
                        .id(2L)
                        .brewerId(3L)
                        .beerId(1L)
                        .startDate(LocalDate.of(2020, 2, 20))
                        .endDate(LocalDate.of(2020, 4, 20))
                        .build()))
                .given(brewService).getBrews();
        // when
        mockMvc.perform(get("/director/brews").header("Authorization", token))
                // then
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"id\" : 1, \n" +
                        "    \"brewerId\" : 5, \n" +
                        "    \"beerId\" : 2, \n" +
                        "    \"startDate\" : \"2020-02-10\",\n" +
                        "    \"endDate\" : \"2020-03-25\" \n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\" : 2, \n" +
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
        final String token = signInAsDirector();
        willReturn(BeerDto.builder()
                .id(3L)
                .type("Wheat")
                .beerName("Summer")
                .abv(4.5)
                .originalGravity(9.0)
                .description("Belgian style wheat beer")
                .ingredients(List.of(IngredientDto.builder()
                                .type(IngredientType.HOPS)
                                .name("Zatec")
                                .build(),
                        IngredientDto.builder()
                                .type(IngredientType.MALT)
                                .name("Wheat Malt")
                                .build(),
                        IngredientDto.builder()
                                .type(IngredientType.YEAST)
                                .name("Yeast")
                                .build()))
                .price(3.5)
                .build())
                .given(beerService).createBeer(BeerDto.builder()
                .type("Wheat")
                .beerName("Summer")
                .abv(4.5)
                .originalGravity(9.0)
                .description("Belgian style wheat beer")
                .ingredients(List.of(IngredientDto.builder()
                                .type(IngredientType.HOPS)
                                .name("Zatec")
                                .build(),
                        IngredientDto.builder()
                                .type(IngredientType.MALT)
                                .name("Wheat Malt")
                                .build(),
                        IngredientDto.builder()
                                .type(IngredientType.YEAST)
                                .name("Yeast")
                                .build()))
                .price(3.5)
                .build());
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

    @Test
    public void testDirectorNewBrewIsCreated() throws Exception {
        // given
        final String token = signInAsDirector();
        willReturn(BrewDto.builder()
                .id(3L)
                .brewerId(5L)
                .beerId(3L)
                .startDate(LocalDate.of(2020, 3, 5))
                .endDate(LocalDate.of(2020, 5, 12))
                .build())
                .given(brewService).createBrew(BrewDto.builder()
                .brewerId(5L)
                .beerId(3L)
                .startDate(LocalDate.of(2020, 3, 5))
                .endDate(LocalDate.of(2020, 5, 12))
                .build());
        // when
        mockMvc.perform(post("/director/brews").header("Authorization", token)
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
                        "    \"id\" : 3,\n" +
                        "    \"brewerId\" : 5, \n" +
                        "    \"beerId\" : 3, \n" +
                        "    \"startDate\" : \"2020-03-05\",\n" +
                        "    \"endDate\" : \"2020-05-12\" \n" +
                        "}"));
    }

    @Test
    public void testDirectorDeleteBeerIsOk() throws Exception {
        // given
        final String token = signInAsDirector();
        // when
        mockMvc.perform(delete("/director/beers/1").header("Authorization", token))
                // then
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDirectorModifyBrewIsOk() throws Exception {
        // given
        final String token = signInAsDirector();
        willReturn(BrewDto.builder()
                .id(2L)
                .brewerId(3L)
                .beerId(1L)
                .startDate(LocalDate.of(2020, 3, 1))
                .endDate(LocalDate.of(2020, 5, 1))
                .build())
                .given(brewService).modifyBrewById(2L, BrewDto.builder()
                .brewerId(null)
                .beerId(null)
                .startDate(LocalDate.of(2020, 3, 1))
                .endDate(LocalDate.of(2020, 5, 1))
                .build());
        // when
        mockMvc.perform(patch("/director/brews/2").header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"startDate\" : \"2020-03-01\",\n" +
                        "    \"endDate\" : \"2020-05-01\" \n" +
                        "}"))
                // then
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"id\" : 2,\n" +
                        "    \"brewerId\" : 3, \n" +
                        "    \"beerId\" : 1, \n" +
                        "    \"startDate\" : \"2020-03-01\",\n" +
                        "    \"endDate\" : \"2020-05-01\" \n" +
                        "}"));
    }

    @Test
    public void testBrewerModifyBrewIsForbidden() throws Exception {
        // given
        final String token = signInAsBrewer();
        // when
        mockMvc.perform(patch("/director/brews/2").header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"startDate\" : \"2020-03-01\",\n" +
                        "    \"endDate\" : \"2020-05-01\" \n" +
                        "}"))
                // then
                .andExpect(status().isForbidden());
    }

    @Test
    public void testBrewerNewBrewIsForbidden() throws Exception {
        // given
        final String token = signInAsBrewer();
        // when
        mockMvc.perform(post("/director/brews").header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"brewerId\" : 5, \n" +
                        "    \"beerId\" : 3, \n" +
                        "    \"startDate\" : \"2020-03-05\",\n" +
                        "    \"endDate\" : \"2020-05-12\" \n" +
                        "}"))
                // then
                .andExpect(status().isForbidden());
    }

    @Test
    public void testCustomerGetBrewListIsForbidden() throws Exception {
        // given
        final String token = signInAsCustomer();
        // when
        mockMvc.perform(get("/director/brews").header("Authorization", token))
                // then
                .andExpect(status().isForbidden());
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
}
