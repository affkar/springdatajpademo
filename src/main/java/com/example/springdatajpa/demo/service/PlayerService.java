package com.example.springdatajpa.demo.service;

import com.example.springdatajpa.demo.model.Player;

public interface PlayerService {

    Player getPlayerById(int playerId);

    Player savePlayer(Player player);

}
