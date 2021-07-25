package com.caniksea.poll.rankinteractive.autogame.config;

import com.caniksea.poll.rankinteractive.autogame.factory.transaction.PromotionFactory;
import com.caniksea.poll.rankinteractive.autogame.factory.transaction.TransactionFactory;
import com.caniksea.poll.rankinteractive.autogame.factory.transaction.TransactionTypeFactory;
import com.caniksea.poll.rankinteractive.autogame.factory.user.PlayerAccountFactory;
import com.caniksea.poll.rankinteractive.autogame.factory.user.PlayerFactory;
import com.caniksea.poll.rankinteractive.autogame.factory.user.PlayerPromotionFactory;
import com.caniksea.poll.rankinteractive.autogame.helper.PasswordHelper;
import com.caniksea.poll.rankinteractive.autogame.helper.StringHelper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean public StringHelper stringHelperTest() {
        return new StringHelper();
    }

    @Bean public PasswordHelper passwordHelperTest() {
        return new PasswordHelper();
    }

    @Bean public TransactionFactory transactionFactoryTest() {
        return new TransactionFactory(stringHelperTest());
    }

    @Bean public PlayerFactory playerFactoryTest() {
        return new PlayerFactory(stringHelperTest());
    }

    @Bean public PlayerAccountFactory playerAccountFactoryTest() {
        return new PlayerAccountFactory(stringHelperTest());
    }

    @Bean public TransactionTypeFactory transactionTypeFactoryTest() {
        return new TransactionTypeFactory(stringHelperTest());
    }

    @Bean public PromotionFactory promotionFactoryTest() {
        return new PromotionFactory(stringHelperTest());
    }

    @Bean public PlayerPromotionFactory playerPromotionFactoryTest() {
        return new PlayerPromotionFactory(stringHelperTest());
    }
}
