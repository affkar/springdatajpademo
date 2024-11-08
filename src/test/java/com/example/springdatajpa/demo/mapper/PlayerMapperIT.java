package com.example.springdatajpa.demo.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.springdatajpa.demo.entity.PlayerE;
import com.example.springdatajpa.demo.model.Player;

@SpringBootTest
public class PlayerMapperIT {

    @Autowired
    PlayerMapper testee;

    @Test
    void testMapToPlayer() {
        int expectedId = 0;
        String expectedName = "Karthick";
        PlayerE playerE = PlayerE.builder().id(expectedId).name(expectedName).build();
        assertThat(testee.mapToPlayer(playerE)).extracting(Player::getId, Player::getName)
                .containsExactly(expectedId, expectedName);
    }

    @Test
    void testMapToPlayerE() {
        int expectedId = 0;
        String expectedName = "Karthick";
        Player player = Player.builder().id(expectedId).name(expectedName).build();
        assertThat(testee.mapToPlayerE(player)).extracting(PlayerE::getId, PlayerE::getName)
                .containsExactly(expectedId, expectedName);
    }
}
