package com.example.springbatchdemo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbatchdemo.model.Player;
import com.example.springbatchdemo.service.PlayerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/player")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/{playerId}")
    public Player getPlayer(@PathVariable Integer playerId) {
        return playerService.getPlayerById(playerId);
    }

    @PostMapping
    public Player savePlayer(@RequestBody Player player) {
        return playerService.savePlayer(player);
    }

}
