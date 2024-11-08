package com.example.springdatajpa.demo.mapper;

import com.example.springdatajpa.demo.entity.PlayerE;
import com.example.springdatajpa.demo.model.Player;

public interface PlayerMapper {

    PlayerE map(Player player);

    Player map(PlayerE playerE);

}
