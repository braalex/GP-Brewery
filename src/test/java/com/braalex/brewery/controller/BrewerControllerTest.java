package com.braalex.brewery.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

import static org.hamcrest.Matchers.hasLength;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BrewerControllerTest extends AbstractControllerTest{

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
                .andExpect(jsonPath("token", hasLength(149)));
    }

    @Test
    public void testBrewerSignInIsOk() throws Exception {
        // given
        final User user = new User("ivanov123@email.com",
                passwordEncoder.encode("ilovebeer"),
                List.of(new SimpleGrantedAuthority("BREWER")));
        given(loadUserDetailService.loadUserByUsername("ivanov123@email.com"))
                .willReturn(user);
        // when
        mockMvc.perform(post("/brewers/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        " \"email\" : \"ivanov123@email.com\",\n" +
                        " \"password\" : \"ilovebeer\"\n" +
                        "}"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("token", hasLength(149)));
    }

    @Test
    public void testBrewerGetBrewListIsOk() throws Exception {
        // given
        final String token = signInAsBrewer();
        // when
        mockMvc.perform(get("/brewers/5/brews").header("Authorization", token))
                // then
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"id\" : 1, \n" +
                        "    \"brewerId\" : 5, \n" +
                        "    \"beerId\" : 2, \n" +
                        "    \"startDate\" : \"2020-02-10\",\n" +
                        "    \"endDate\" : \"2020-03-25\" \n" +
                        "  }\n" +
                        "]"));
    }

    @Test
    public void testCustomerGetBrewListIsForbidden() throws Exception {
        // given
        final String token = signInAsCustomer();
        // when
        mockMvc.perform(get("/brewers/5/brews").header("Authorization", token))
                // then
                .andExpect(status().isForbidden());
    }
}
