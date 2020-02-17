package com.braalex.brewery.controller;

import com.braalex.brewery.mock.BrewControllerMockData;
import com.braalex.brewery.service.BrewService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;

import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BrewControllerTest extends AbstractControllerTest {

    @SpyBean
    private BrewService brewService;

    @Test
    public void testBrewerGetBrewListIsOk() throws Exception {
        // given
        final String token = signInAsBrewer();
        willReturn(BrewControllerMockData.getBrewsByBrewer())
                .given(brewService).getBrewsByBrewerId(5L);
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

    @Test
    public void testDirectorModifyBrewIsOk() throws Exception {
        // given
        final String token = signInAsDirector();
        willReturn(BrewControllerMockData.getBrew())
                .given(brewService).modifyBrewById(2L, BrewControllerMockData.getBrewForModifying());
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
    public void testDirectorGetBrewListIsOk() throws Exception {
        // given
        final String token = signInAsDirector();
        willReturn(BrewControllerMockData.getBrewList())
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
    public void testDirectorNewBrewIsCreated() throws Exception {
        // given
        final String token = signInAsDirector();
        willReturn(BrewControllerMockData.getNewBrew())
                .given(brewService).createBrew(BrewControllerMockData.postNewBrew());
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
}
