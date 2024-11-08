package com.example.springdatajpa.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.springdatajpa.demo.service.DataNotFoundException;
import com.example.springdatajpa.demo.service.PlayerService;

@SpringBootTest
public class PlayerServiceImplIT {

    @Autowired
    private PlayerService playerService;

    @Test
    void testGetPlayerById() {
        final Throwable actualException = catchThrowable(() -> playerService.getPlayerById(0));
        assertThat(actualException).isInstanceOf(DataNotFoundException.class)
                .hasMessageContaining("No Such Player with Id: 0");
    }

    @Test
    void testSavePlayer() {

    }
}
