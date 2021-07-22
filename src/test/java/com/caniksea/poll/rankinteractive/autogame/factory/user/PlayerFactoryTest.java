package com.caniksea.poll.rankinteractive.autogame.factory.user;

import com.caniksea.poll.rankinteractive.autogame.entity.user.Player;
import com.caniksea.poll.rankinteractive.autogame.helper.StringHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
class PlayerFactoryTest {

    @TestConfiguration
    static class TestConfig{
        @Bean
        public StringHelper stringHelper() {
            return new StringHelper();
        }
    }

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