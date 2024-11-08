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
    public Player getPlayerById(final int playerId) {
        return this.playerRepository.findById(playerId).map(playerE -> this.playerMapper.mapToPlayer(playerE))
                .orElseThrow(() -> new DataNotFoundException("No Such Player with Id: " + playerId));
    }

    @Override
    public Player savePlayer(final Player player) {
        final PlayerE playerE = Optional.of(player).map(p -> this.playerMapper.mapToPlayerE(p)).orElseThrow(
                () -> new IllegalArgumentException("Expected a Person Object as the payload. Didn't find one!"));
        return Optional.of(this.playerRepository.save(playerE)).map(pE -> this.playerMapper.mapToPlayer(pE))
                .orElseThrow(
                        () -> new IllegalArgumentException("Hibernate Gave me an empty Player Entity when saving"));
    }

}
