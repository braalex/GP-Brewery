package com.braalex.brewery.controller;

import com.braalex.brewery.dto.UserSignInResponseDto;
import com.braalex.brewery.entity.AuthInfoEntity;
import com.braalex.brewery.entity.CustomerEntity;
import com.braalex.brewery.entity.StaffEntity;
import com.braalex.brewery.entity.UserEntity;
import com.braalex.brewery.repository.AuthInfoRepository;
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
public abstract class AbstractControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected PasswordEncoder passwordEncoder;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    protected AuthInfoRepository authInfoRepository;
    @MockBean
    protected UserRepository userRepository;

    protected String signInAsCustomer() throws Exception {
        final AuthInfoEntity authInfo = createCustomerAuthInfo();
        willReturn(Optional.of(authInfo)).given(authInfoRepository).findByLogin("craft-bar@email.com");

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

    protected AuthInfoEntity createCustomerAuthInfo() {
        final UserEntity customer = new CustomerEntity();
        customer.setUserRole(UserRole.CUSTOMER);
        customer.setEmail("craft-bar@email.com");

        final AuthInfoEntity customerAuthInfo = new AuthInfoEntity();
        customerAuthInfo.setLogin(customer.getEmail());
        customerAuthInfo.setPassword(passwordEncoder.encode("qwerty"));
        customerAuthInfo.setUser(customer);
        return customerAuthInfo;
    }

    protected String signInAsBrewer() throws Exception {
        final AuthInfoEntity authInfo = createBrewerAuthInfo();
        willReturn(Optional.of(authInfo)).given(authInfoRepository).findByLogin("ivanov123@email.com");

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

    protected AuthInfoEntity createBrewerAuthInfo() {
        final UserEntity brewer = new StaffEntity();
        brewer.setUserRole(UserRole.BREWER);
        brewer.setEmail("ivanov123@email.com");

        final AuthInfoEntity brewerAuthInfo = new AuthInfoEntity();
        brewerAuthInfo.setLogin(brewer.getEmail());
        brewerAuthInfo.setPassword(passwordEncoder.encode("ilovebeer"));
        brewerAuthInfo.setUser(brewer);
        return brewerAuthInfo;
    }

    protected String signInAsDirector() throws Exception {
        final AuthInfoEntity authInfo = createDirectorAuthInfo();
        willReturn(Optional.of(authInfo)).given(authInfoRepository).findByLogin( "bigboss@email.com");

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

    protected AuthInfoEntity createDirectorAuthInfo() {
        final UserEntity director = new StaffEntity();
        director.setUserRole(UserRole.DIRECTOR);
        director.setEmail("bigboss@email.com");

        final AuthInfoEntity directorAuthInfo = new AuthInfoEntity();
        directorAuthInfo.setLogin(director.getEmail());
        directorAuthInfo.setPassword(passwordEncoder.encode("secretpass"));
        directorAuthInfo.setUser(director);
        return directorAuthInfo;
    }
}
