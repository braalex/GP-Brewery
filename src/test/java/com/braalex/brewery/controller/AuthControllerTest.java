package com.braalex.brewery.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.Optional;

import static org.hamcrest.Matchers.hasLength;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthControllerTest extends AbstractControllerTest {

    @Test
    public void testCustomerSignUpIsCreated() throws Exception {
        // given
        willReturn(Optional.empty(), Optional.of(createCustomerAuthInfo()))
                .given(authInfoRepository).findByLogin("craft-bar@email.com");
        // when
        mockMvc.perform(post("/customers/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        " \"email\" : \"craft-bar@email.com\",\n" +
                        " \"password\" : \"qwerty\",\n" +
                        " \"category\" : \"bar\",\n" +
                        " \"companyName\" : \"Craft Bar\" \n" +
                        "}"))
                // then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("token", hasLength(149)));
    }

    @Test
    public void testCustomerSignUpWhenUserAlreadyExisted() throws Exception {
        // given
        signInAsCustomer();
        // when
        mockMvc.perform(post("/customers/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        " \"email\" : \"craft-bar@email.com\",\n" +
                        " \"password\" : \"qwerty\",\n" +
                        " \"category\" : \"bar\",\n" +
                        " \"companyName\" : \"Craft Bar\" \n" +
                        "}"))
                // then
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCustomerSignInIsOk() throws Exception {
        // given
        signInAsCustomer();
        // when
        mockMvc.perform(post("/customers/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        " \"email\" : \"craft-bar@email.com\",\n" +
                        " \"password\" : \"qwerty\"\n" +
                        "}"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("token", hasLength(149)));
    }

    @Test
    public void testBrewerSignUpIsCreated() throws Exception {
        // given
        willReturn(Optional.empty(), Optional.of(createBrewerAuthInfo()))
                .given(authInfoRepository).findByLogin("ivanov123@email.com");
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
        signInAsBrewer();
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
        signInAsBrewer();
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
    public void testDirectorSignUpIsCreated() throws Exception {
        // given
        willReturn(Optional.empty(), Optional.of(createDirectorAuthInfo()))
                .given(authInfoRepository).findByLogin("bigboss@email.com");
        // when
        mockMvc.perform(post("/director/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        " \"email\" : \"bigboss@email.com\",\n" +
                        " \"password\" : \"secretpass\",\n" +
                        " \"firstName\" : \"Anton\",\n" +
                        " \"lastName\" : \"Pivovarov\",\n" +
                        " \"dateOfBirth\" : \"1985-02-20\" \n" +
                        "}"))
                // then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("token", hasLength(147)));
    }

    @Test
    public void testDirectorSignInIsOk() throws Exception {
        // given
        signInAsDirector();
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
        signInAsDirector();
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
}
