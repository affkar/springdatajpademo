package com.example.springdatajpa.demo.testhelpers;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springdatajpa.demo.repository.PlayerRepository;

@Component
public class TestHelper {

    @Autowired
    private PlayerRepository playerRepository;

    public void assertCleanSlate() {
        assertThat(this.playerRepository.findAll()).isEmpty();
    }

    public void cleanupTransactionalData() {
        this.playerRepository.deleteAll();
    }
}
