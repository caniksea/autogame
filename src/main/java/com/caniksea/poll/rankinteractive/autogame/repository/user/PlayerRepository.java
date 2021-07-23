package com.caniksea.poll.rankinteractive.autogame.repository.user;

import com.caniksea.poll.rankinteractive.autogame.entity.user.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {
    Optional<Player> findByUsername(String username);
}
