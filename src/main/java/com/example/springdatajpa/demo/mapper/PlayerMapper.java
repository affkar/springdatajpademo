package com.example.springdatajpa.demo.mapper;

import com.example.springdatajpa.demo.entity.PlayerE;
import com.example.springdatajpa.demo.model.Player;

public interface PlayerMapper {

    PlayerE mapToPlayerE(Player player);

    Player mapToPlayer(PlayerE playerE);

}
