package com.caniksea.poll.rankinteractive.autogame.factory.transaction;

import com.caniksea.poll.rankinteractive.autogame.config.TestConfig;
import com.caniksea.poll.rankinteractive.autogame.entity.transaction.Promotion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Import(TestConfig.class)
class PromotionFactoryTest {

    @Autowired private PromotionFactory factory;

    @Test
    void build() {
        Optional<Promotion> promotion = this.factory.build("paper", "test promotion", 5,
                LocalDate.of(2021, 07, 23), LocalDate.of(2021, 11, 10));
        assertAll(
                () -> assertTrue(promotion.isPresent()),
                () -> assertNotNull(promotion.get().getCode())
        );
    }
}