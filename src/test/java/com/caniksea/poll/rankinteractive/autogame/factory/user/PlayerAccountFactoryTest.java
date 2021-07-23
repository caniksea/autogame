package com.caniksea.poll.rankinteractive.autogame.factory.user;

import com.caniksea.poll.rankinteractive.autogame.config.TestConfig;
import com.caniksea.poll.rankinteractive.autogame.entity.user.PlayerAccount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Import(TestConfig.class)
class PlayerAccountFactoryTest {

    @Autowired private PlayerAccountFactory factory;

    @Test void build() {
        Optional<PlayerAccount> entity = this.factory.build("9", BigDecimal.TEN);
        assertTrue(entity.isPresent());
    }
}