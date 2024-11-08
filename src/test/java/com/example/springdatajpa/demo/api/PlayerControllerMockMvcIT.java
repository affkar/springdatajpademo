package com.example.springdatajpa.demo.api;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.springdatajpa.demo.service.DataNotFoundException;
import com.example.springdatajpa.demo.service.PlayerService;

@WebMvcTest
public class PlayerControllerMockMvcIT {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PlayerService playerService;

    @Test
    void testGetPlayer() throws Exception {
        given(playerService.getPlayerById(1)).willThrow(new DataNotFoundException("Could not find player"));
        MvcResult mvcResult = mockMvc.perform(get(UriComponentsBuilder.fromPath("/api/player/{playerId}").build("1"))
                .accept(org.springframework.http.MediaType.APPLICATION_JSON))

                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage", Matchers.equalTo("Could not find player")))
                .andExpect(jsonPath("$.statusCode", Matchers.equalTo("NOT_FOUND")))
                .andReturn();
    }

    @Test
    void testGetPlayerPages() {

    }

    @Test
    void testGetPlayers() {

    }

    @Test
    void testSavePlayer() {

    }
}
