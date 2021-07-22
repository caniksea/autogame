package com.caniksea.poll.rankinteractive.autogame.config;

import com.caniksea.poll.rankinteractive.autogame.helper.StringHelper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean public StringHelper stringHelper() {
        return new StringHelper();
    }
}
