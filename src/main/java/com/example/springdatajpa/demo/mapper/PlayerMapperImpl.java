package com.example.springdatajpa.demo.mapper;

import org.springframework.stereotype.Service;

import com.example.springdatajpa.demo.entity.PlayerE;
import com.example.springdatajpa.demo.model.Player;

@Service
public class PlayerMapperImpl implements PlayerMapper {

    @Override
    public PlayerE mapToPlayerE(Player player) {
        return PlayerE.builder()
                .id(player.getId())
                .name(player.getName())
                .build();

    }

    @Override
    public Player mapToPlayer(PlayerE playerE) {
        return Player.builder()
                .id(playerE.getId())
                .name(playerE.getName())
                .build();
    }

}
