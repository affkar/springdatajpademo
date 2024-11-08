package com.example.springdatajpa.demo.api;

import static org.assertj.core.api.Assertions.assertThat;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.BDDMockito.given;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.springdatajpa.demo.model.Player;
import com.example.springdatajpa.demo.service.DataNotFoundException;
import com.example.springdatajpa.demo.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class PlayerControllerMockMvc {

        @Autowired
        MockMvc mockMvc;

        @MockBean
        PlayerService playerService;

        @Autowired
        ObjectMapper objectMapper;

        @Captor
        ArgumentCaptor<Player> playerCaptor;

        @Nested
        class GetPlayer {

                @Test
                void Not_found() throws Exception {
                        given(PlayerControllerMockMvc.this.playerService.getPlayerById(1))
                                        .willThrow(new DataNotFoundException("Could not find player"));
                        PlayerControllerMockMvc.this.mockMvc
                                        .perform(get(UriComponentsBuilder.fromPath("/api/player/{playerId}").build("1"))
                                                        .accept(org.springframework.http.MediaType.APPLICATION_JSON))

                                        .andExpect(status().isNotFound())
                                        .andExpect(jsonPath("$.errorMessage",
                                                        Matchers.equalTo("Could not find player")))
                                        .andExpect(jsonPath("$.statusCode", Matchers.equalTo("NOT_FOUND")))
                                        .andReturn();
                }

                @Test
                void ok() throws Exception {
                        given(PlayerControllerMockMvc.this.playerService.getPlayerById(1))
                                        .willReturn(Player.builder().id(1).name("Alagu").build());
                        PlayerControllerMockMvc.this.mockMvc
                                        .perform(get(UriComponentsBuilder.fromPath("/api/player/{playerId}").build("1"))
                                                        .accept(org.springframework.http.MediaType.APPLICATION_JSON))
                                        .andExpect(status().isOk())
                                        .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                                        .andExpect(jsonPath("$.name", Matchers.equalTo("Alagu")))
                                        .andReturn();
                }
        }

        @Nested
        class SavePlayer {

                @Test
                void ok() throws Exception {
                        final Player playerToSave = Player.builder().name("Azhagu").build();
                        final Player savedPlayer = Player.builder().id(1).name("Azhagu").build();
                        given(PlayerControllerMockMvc.this.playerService
                                        .savePlayer(PlayerControllerMockMvc.this.playerCaptor.capture()))
                                        .willReturn(savedPlayer);
                        PlayerControllerMockMvc.this.mockMvc.perform(post("/api/player")
                                        .accept(org.springframework.http.MediaType.APPLICATION_JSON)
                                        .content(PlayerControllerMockMvc.this.objectMapper
                                                        .writeValueAsString(playerToSave))
                                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON))
                                        .andExpect(status().isOk())
                                        .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                                        .andExpect(jsonPath("$.name", Matchers.equalTo("Azhagu")))
                                        .andReturn();

                        assertThat(PlayerControllerMockMvc.this.playerCaptor.getValue())
                                        .extracting(Player::getId, Player::getName)
                                        .containsExactly(null, "Azhagu");
                }

                @Test
                void Internal_Server_Error() throws Exception {
                        final Player playerToSave = Player.builder().name("Azhagu").build();
                        given(PlayerControllerMockMvc.this.playerService
                                        .savePlayer(PlayerControllerMockMvc.this.playerCaptor.capture())).willThrow(
                                                        new RuntimeException(
                                                                        "Sorry there was an internal Server Error. Please contact Support"));
                        PlayerControllerMockMvc.this.mockMvc.perform(post("/api/player")
                                        .accept(org.springframework.http.MediaType.APPLICATION_JSON)
                                        .content(PlayerControllerMockMvc.this.objectMapper
                                                        .writeValueAsString(playerToSave))
                                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON))
                                        .andExpect(status().isInternalServerError())
                                        .andExpect(jsonPath("$.errorMessage",
                                                        Matchers.equalTo(
                                                                        "Sorry there was an internal Server Error. Please contact Support")))
                                        .andExpect(jsonPath("$.statusCode", Matchers.equalTo("INTERNAL_SERVER_ERROR")))
                                        .andReturn();

                        assertThat(PlayerControllerMockMvc.this.playerCaptor.getValue())
                                        .extracting(Player::getId, Player::getName)
                                        .containsExactly(null, "Azhagu");
                }
        }

        @Test
        void testGetPlayerPages() {

        }

        @Test
        void testGetPlayers() {

        }

}
