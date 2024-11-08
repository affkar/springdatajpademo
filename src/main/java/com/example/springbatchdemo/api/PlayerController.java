package com.example.springbatchdemo.api;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbatchdemo.model.Player;
import com.example.springbatchdemo.model.PlayerPages;
import com.example.springbatchdemo.service.PlayerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
@Slf4j
public class PlayerController implements PlayerApi {

    public static final int COUNT = 1000000;

    private final PlayerService playerService;

    @Override
    public Player getPlayer(Integer playerId) {
        return playerService.getPlayerById(playerId);
    }

    @Override
    public Player savePlayer(Player player) {
        return playerService.savePlayer(player);
    }

    @Override
    public PlayerPages getPlayerPages(Instant queryDate) {
        log.info("queryDate was " + queryDate);
        return PlayerPages.builder().count(COUNT).build();
    }

    @Override
    public List<Player> getPlayers(Integer pageNo) {
        // if (pageNo == 100) {
        // throw new RuntimeException("Can't handle load at page no. 100");
        // }
        return IntStream.rangeClosed((500 * (pageNo - 1)) + 1, 500 * pageNo)
                .boxed()
                .map(i -> Player.builder().id(i).name("player" + i).build())
                .collect(Collectors.toList());
    }

}
