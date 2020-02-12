package com.braalex.brewery.controller;

import com.braalex.brewery.dto.UserSignInResponseDto;
import com.braalex.brewery.security.LoadUserDetailService;
import com.braalex.brewery.security.Roles;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AbstractControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected PasswordEncoder passwordEncoder;
    @Autowired
    private ObjectMapper objectMapper;

    @SpyBean
    protected LoadUserDetailService loadUserDetailService;

    protected String signInAsCustomer() throws Exception {
        final User user = new User("craft-bar@email.com",
                passwordEncoder.encode("qwerty"),
                List.of(new SimpleGrantedAuthority("ROLE_" + Roles.CUSTOMER.name())));

        when(loadUserDetailService.loadUserByUsername("craft-bar@email.com")).thenReturn(user);

        final String response = mockMvc.perform(post("/customers/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"craft-bar@email.com\",\n" +
                        "  \"password\" : \"qwerty\"\n" +
                        "}"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        return "Bearer " + objectMapper.readValue(response, UserSignInResponseDto.class).getToken();
    }

    protected String signInAsBrewer() throws Exception {
        final User user = new User("ivanov123@email.com",
                passwordEncoder.encode("ilovebeer"),
                List.of(new SimpleGrantedAuthority("ROLE_" + Roles.BREWER.name())));

        when(loadUserDetailService.loadUserByUsername("ivanov123@email.com")).thenReturn(user);

        final String response = mockMvc.perform(post("/brewers/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"ivanov123@email.com\",\n" +
                        "  \"password\" : \"ilovebeer\"\n" +
                        "}"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        return "Bearer " + objectMapper.readValue(response, UserSignInResponseDto.class).getToken();
    }

    protected String signInAsDirector() throws Exception {
        final User user = new User("bigboss@email.com",
                passwordEncoder.encode("secretpass"),
                List.of(new SimpleGrantedAuthority("ROLE_" + Roles.DIRECTOR.name())));

        when(loadUserDetailService.loadUserByUsername("bigboss@email.com")).thenReturn(user);

        final String response = mockMvc.perform(post("/director/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"bigboss@email.com\",\n" +
                        "  \"password\" : \"secretpass\"\n" +
                        "}"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        return "Bearer " + objectMapper.readValue(response, UserSignInResponseDto.class).getToken();
    }
}
