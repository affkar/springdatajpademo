package com.example.springdatajpa.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.springdatajpa.demo.entity.PlayerE;
import com.example.springdatajpa.demo.mapper.PlayerMapper;
import com.example.springdatajpa.demo.model.Player;
import com.example.springdatajpa.demo.repository.PlayerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    @Override
    public Player getPlayerById(int playerId) {
        return playerRepository.findById(playerId).map(playerE -> playerMapper.map(playerE))
                .orElseThrow(() -> new DataNotFoundException("No Such Player with Id: " + playerId));
    }

    @Override
    public Player savePlayer(Player player) {
        PlayerE playerE = Optional.of(player).map(p -> playerMapper.map(p)).orElseThrow(
                () -> new IllegalArgumentException("Expected a Person Object as the payload. Didn't find one!"));
        return Optional.of(playerRepository.save(playerE)).map(pE -> playerMapper.map(pE)).orElseThrow(
                () -> new IllegalArgumentException("Hibernate Gave me an empty Player Entity when saving"));
    }

}
