package com.caniksea.poll.rankinteractive.autogame.factory.user;

import com.caniksea.poll.rankinteractive.autogame.entity.user.PlayerAccount;
import com.caniksea.poll.rankinteractive.autogame.helper.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class PlayerAccountFactory {

    private StringHelper stringHelper;

    @Autowired public PlayerAccountFactory(StringHelper stringHelper) {
        this.stringHelper = stringHelper;
    }

    public Optional<PlayerAccount> build(String playerId, BigDecimal balance) {
        PlayerAccount playerAccount = null;
        if (!this.stringHelper.isEmptyOrNull(playerId))
            playerAccount = PlayerAccount.builder()
                    .playerId(playerId).balance(balance).build();
        return Optional.ofNullable(playerAccount);
    }
}
