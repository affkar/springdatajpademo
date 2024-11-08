package com.example.springdatajpa.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.springdatajpa.demo.entity.PlayerE;

@Repository
public interface PlayerRepository extends CrudRepository<PlayerE, Integer> {

}
