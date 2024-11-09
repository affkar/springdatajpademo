package com.example.springdatajpa.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.springdatajpa.demo.model.DataNotFoundException;
import com.example.springdatajpa.demo.model.Player;
import com.example.springdatajpa.demo.testhelpers.TestHelper;

@SpringBootTest
public class PlayerServiceImplIT {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TestHelper testHelper;

    @BeforeEach
    void setup() {
        this.testHelper.assertCleanSlate();
    }

    @AfterEach
    void tearDown() {
        this.testHelper.cleanupTransactionalData();
    }

    @Test
    void getPlayerById_404_when_NoSuchPlayerExistsInTheRepoForTheId() {
        final Throwable actualException = catchThrowable(() -> this.playerService.getPlayerById(0));
        assertThat(actualException).isInstanceOf(DataNotFoundException.class)
                .hasMessageContaining("No Such Player with Id: 0");
    }

    @Test
    void getPlayerById_200_When_A_PlayerHasBeenSavedAndItExistsInTheRepo() {
        final Player savedPlayer = this.playerService.savePlayer(Player.builder().name("Karthick").build());

    }
}
