package com.example.springbatchdemo.mapper;

import org.springframework.stereotype.Service;

import com.example.springbatchdemo.entity.PlayerE;
import com.example.springbatchdemo.model.Player;

@Service
public class PlayerMapperImpl implements PlayerMapper {

    @Override
    public PlayerE map(Player player) {
        return PlayerE.builder()
                .id(player.getId())
                .name(player.getName())
                .address1(player.getAddress1())
                .address2(player.getAddress2())
                .build();

    }

    @Override
    public Player map(PlayerE playerE) {
        return Player.builder()
                .id(playerE.getId())
                .name(playerE.getName())
                .address1(playerE.getAddress1())
                .address2(playerE.getAddress2())
                .build();
    }

}
