package com.example.springdatajpa.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.springdatajpa.demo.entity.PlayerE;

@DataJpaTest
public class PlayerRepositoryDataJpaIT {

    @Autowired
    PlayerRepository testee;

    @Test
    void testSave() {
        PlayerE savedPlayerEntity = testee.save(PlayerE.builder().name("Alagu").build());
        assertThat(savedPlayerEntity.getId()).isNotNull();
        Optional<PlayerE> retrievedPlayerEntity = testee.findById(savedPlayerEntity.getId());
        assertThat(retrievedPlayerEntity).isNotEmpty();
        assertThat(retrievedPlayerEntity.get())
                .extracting(PlayerE::getId, PlayerE::getName)
                .containsExactly(savedPlayerEntity.getId(), savedPlayerEntity.getName());
    }

}
