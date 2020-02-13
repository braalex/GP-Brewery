package com.braalex.brewery.controller;

import com.braalex.brewery.dto.UserSignInResponseDto;
import com.braalex.brewery.entity.CustomerEntity;
import com.braalex.brewery.entity.StaffEntity;
import com.braalex.brewery.entity.UserEntity;
import com.braalex.brewery.repository.UserRepository;
import com.braalex.brewery.security.UserRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.willReturn;
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

    @MockBean
    protected UserRepository userRepository;

    protected String signInAsCustomer() throws Exception {
        final UserEntity user = createCustomerInfo();
        willReturn(Optional.of(user)).given(userRepository).findByEmail("craft-bar@email.com");

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

    protected UserEntity createCustomerInfo() {
        final UserEntity customer = new CustomerEntity();
        customer.setUserRole(UserRole.CUSTOMER);
        customer.setEmail("craft-bar@email.com");
        customer.setPassword(passwordEncoder.encode("qwerty"));
        return customer;
    }

    protected String signInAsBrewer() throws Exception {
        final UserEntity user = createBrewerInfo();
        willReturn(Optional.of(user)).given(userRepository).findByEmail("ivanov123@email.com");

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

    protected UserEntity createBrewerInfo() {
        final UserEntity brewer = new StaffEntity();
        brewer.setUserRole(UserRole.BREWER);
        brewer.setEmail("ivanov123@email.com");
        brewer.setPassword(passwordEncoder.encode("ilovebeer"));
        return brewer;
    }

    protected String signInAsDirector() throws Exception {
        final UserEntity user = createDirectorInfo();
        willReturn(Optional.of(user)).given(userRepository).findByEmail("bigboss@email.com");

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

    protected UserEntity createDirectorInfo() {
        final UserEntity director = new StaffEntity();
        director.setUserRole(UserRole.DIRECTOR);
        director.setEmail("bigboss@email.com");
        director.setPassword(passwordEncoder.encode("secretpass"));
        return director;
    }
}
