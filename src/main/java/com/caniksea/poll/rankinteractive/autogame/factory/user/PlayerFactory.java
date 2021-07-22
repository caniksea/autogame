package com.caniksea.poll.rankinteractive.autogame.factory.user;

import com.caniksea.poll.rankinteractive.autogame.entity.user.Player;
import com.caniksea.poll.rankinteractive.autogame.helper.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PlayerFactory {

    private StringHelper stringHelper;

    @Autowired public PlayerFactory(StringHelper stringHelper) {
        this.stringHelper = stringHelper;
    }

    public Optional<Player> build(String username, String firstName, String lastName) {
        Player player = null;
        if (!this.stringHelper.isEmptyOrNull(username)) {
            player = Player.builder()
                    .id(this.stringHelper.generateId())
                    .username(username)
                    .firstName(this.stringHelper.setEmptyIfNull(firstName))
                    .lastName(this.stringHelper.setEmptyIfNull(lastName))
                    .build();
        }
        return Optional.ofNullable(player);
    }
}
