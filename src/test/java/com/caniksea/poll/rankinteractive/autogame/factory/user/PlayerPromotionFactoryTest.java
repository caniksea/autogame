package com.caniksea.poll.rankinteractive.autogame.factory.user;

import com.caniksea.poll.rankinteractive.autogame.config.TestConfig;
import com.caniksea.poll.rankinteractive.autogame.entity.user.PlayerPromotion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Import(TestConfig.class)
class PlayerPromotionFactoryTest {

    @Autowired private PlayerPromotionFactory factory;

    @Test
    void build() {
        Optional<PlayerPromotion> playerPromotion = this.factory.build("001", "001", "paper");
        assertAll(
                () -> assertTrue(playerPromotion.isPresent()),
                () -> assertNotNull(playerPromotion.get().getTransactionId())
        );
    }
}