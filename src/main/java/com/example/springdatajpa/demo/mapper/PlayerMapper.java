package com.example.springdatajpa.demo.mapper;

import org.mapstruct.Mapper;

import com.example.springdatajpa.demo.entity.PlayerE;
import com.example.springdatajpa.demo.model.Player;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    PlayerE mapToPlayerE(Player player);

    Player mapToPlayer(PlayerE playerE);

}
