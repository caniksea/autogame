package com.caniksea.poll.rankinteractive.autogame.factory.user;

import com.caniksea.poll.rankinteractive.autogame.config.TestConfig;
import com.caniksea.poll.rankinteractive.autogame.entity.user.Player;
import com.caniksea.poll.rankinteractive.autogame.helper.StringHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@Import(TestConfig.class)
class PlayerFactoryTest {

    private PlayerFactory playerFactory;
    @Autowired StringHelper stringHelper;

    @BeforeEach void setup() {
        assertNotNull(this.stringHelper);
        this.playerFactory = new PlayerFactory(this.stringHelper);
    }

    @Test void build() {
        Optional<Player> player = this.playerFactory.build("helen-game", "", "");
        System.out.println(player);
        assertNotNull(player.get());
    }
}