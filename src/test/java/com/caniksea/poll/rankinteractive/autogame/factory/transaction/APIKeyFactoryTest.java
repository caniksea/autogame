package com.caniksea.poll.rankinteractive.autogame.factory.transaction;

import com.caniksea.poll.rankinteractive.autogame.config.TestConfig;
import com.caniksea.poll.rankinteractive.autogame.entity.APIConstant;
import com.caniksea.poll.rankinteractive.autogame.entity.transaction.APIKey;
import com.caniksea.poll.rankinteractive.autogame.helper.PasswordHelper;
import com.caniksea.poll.rankinteractive.autogame.helper.StringHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Import(TestConfig.class)
class APIKeyFactoryTest {

    @Autowired private StringHelper stringHelper;
    @Autowired private PasswordHelper passwordHelper;
    private APIKeyFactory factory;

    @BeforeEach void setup() {
        this.factory = new APIKeyFactory(stringHelper, passwordHelper);
    }

    @Test
    void build() {
        Optional<APIKey> apiKey = this.factory.build(APIConstant.KEY_CS_SUPPORT.value, "swordfish");
        assertAll(
                () -> assertTrue(apiKey.isPresent()),
                () -> assertNotSame("swordfish", apiKey.get().getValue())
        );
    }
}