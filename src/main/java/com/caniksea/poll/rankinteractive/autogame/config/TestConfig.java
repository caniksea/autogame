package com.caniksea.poll.rankinteractive.autogame.config;

import com.caniksea.poll.rankinteractive.autogame.entity.user.PlayerAccount;
import com.caniksea.poll.rankinteractive.autogame.factory.transaction.APIKeyFactory;
import com.caniksea.poll.rankinteractive.autogame.factory.transaction.TransactionFactory;
import com.caniksea.poll.rankinteractive.autogame.factory.transaction.TransactionTypeFactory;
import com.caniksea.poll.rankinteractive.autogame.factory.user.PlayerAccountFactory;
import com.caniksea.poll.rankinteractive.autogame.factory.user.PlayerFactory;
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

    @Bean public APIKeyFactory apiKeyFactoryTest() {
        return new APIKeyFactory(stringHelperTest(), passwordHelperTest());
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
}
