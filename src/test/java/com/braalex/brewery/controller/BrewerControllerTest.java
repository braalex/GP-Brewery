package com.braalex.brewery.controller;

import com.braalex.brewery.dto.BrewDto;
import com.braalex.brewery.service.BrewService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BrewerControllerTest extends AbstractControllerTest{

    @SpyBean
    private BrewService brewService;

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
        willReturn(user).given(loadUserDetailService).loadUserByUsername("ivanov123@email.com");
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
    public void testBrewerSignInWithWrongEmail() throws Exception {
        // given
        final User user = new User("ivanov123@email.com",
                passwordEncoder.encode("ilovebeer"),
                List.of(new SimpleGrantedAuthority("BREWER")));
        willReturn(user).given(loadUserDetailService).loadUserByUsername("ivanov123@email.com");
        // when
        mockMvc.perform(post("/brewers/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        " \"email\" : \"ivanov123@mail.ru\",\n" +
                        " \"password\" : \"ilovebeer\"\n" +
                        "}"))
                // then
                .andExpect(status().isForbidden());
    }

    @Test
    public void testBrewerGetBrewListIsOk() throws Exception {
        // given
        final String token = signInAsBrewer();
        willReturn(List.of(BrewDto.builder()
                        .id(1L)
                        .brewerId(5L)
                        .beerId(2L)
                        .startDate(LocalDate.of(2020, 2, 10))
                        .endDate(LocalDate.of(2020, 3, 25))
                        .build()))
                .given(brewService).getBrewsByBrewer(5L);
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
