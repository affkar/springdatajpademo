
package com.example.springdatajpa.demo.api;

import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.springdatajpa.demo.model.Player;
import com.example.springdatajpa.demo.testhelpers.TestHelper;

import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class PlayerControllerIT {

    @Autowired
    private WebTestClient webClient;

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
    void testWithWebTestClient() {
        final AtomicInteger atomicInteger = new AtomicInteger();
        this.webClient
                .post().uri("/api/player")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(Player.builder().name("Karthick").build()), Player.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Player.class)
                .value(player -> {
                    assertThat(player).isNotNull();
                    assertThat(player.getName()).isEqualTo("Karthick");
                    assertThat(player.getId()).isNotNull();
                    atomicInteger.set(player.getId());
                });

        this.webClient
                .get().uri("/api/player/" + atomicInteger.get())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Player.class)
                .value(player -> {
                    assertThat(player).isNotNull();
                    assertThat(player.getName()).isEqualTo("Karthick");
                    assertThat(player.getId()).isEqualTo(atomicInteger.get());
                });

    }

}