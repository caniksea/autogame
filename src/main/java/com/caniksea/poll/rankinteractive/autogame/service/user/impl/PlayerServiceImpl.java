package com.caniksea.poll.rankinteractive.autogame.service.user.impl;

import com.caniksea.poll.rankinteractive.autogame.entity.user.Player;
import com.caniksea.poll.rankinteractive.autogame.repository.user.PlayerRepository;
import com.caniksea.poll.rankinteractive.autogame.service.user.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository repository;

    @Autowired public PlayerServiceImpl(PlayerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Player create(Player player) {
        return this.repository.save(player);
    }

    @Override
    public Optional<Player> readById(String id) {
        return this.repository.findById(id);
    }

    @Override
    public List<Player> findAll() {
        return this.repository.findAll();
    }
}
