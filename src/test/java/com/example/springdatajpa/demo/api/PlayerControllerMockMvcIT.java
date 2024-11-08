package com.example.springdatajpa.demo.api;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.springdatajpa.demo.model.Player;
import com.example.springdatajpa.demo.service.DataNotFoundException;
import com.example.springdatajpa.demo.service.PlayerService;

@WebMvcTest
public class PlayerControllerMockMvcIT {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PlayerService playerService;

    @Nested

    class GetPlayer {

        @Test
        void Not_found() throws Exception {
            given(playerService.getPlayerById(1)).willThrow(new DataNotFoundException("Could not find player"));
            mockMvc.perform(get(UriComponentsBuilder.fromPath("/api/player/{playerId}").build("1"))
                    .accept(org.springframework.http.MediaType.APPLICATION_JSON))

                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.errorMessage", Matchers.equalTo("Could not find player")))
                    .andExpect(jsonPath("$.statusCode", Matchers.equalTo("NOT_FOUND")))
                    .andReturn();
        }

        @Test
        void ok() throws Exception {
            given(playerService.getPlayerById(1)).willReturn(Player.builder().id(1).name("Alagu").build());
            mockMvc.perform(get(UriComponentsBuilder.fromPath("/api/player/{playerId}").build("1"))
                    .accept(org.springframework.http.MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                    .andExpect(jsonPath("$.name", Matchers.equalTo("Alagu")))
                    .andReturn();
        }
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
