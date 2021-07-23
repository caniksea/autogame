package com.caniksea.poll.rankinteractive.autogame.factory.transaction;

import com.caniksea.poll.rankinteractive.autogame.config.TestConfig;
import com.caniksea.poll.rankinteractive.autogame.entity.transaction.TransactionType;
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
class TransactionTypeFactoryTest {

    @Autowired private TransactionTypeFactory transactionTypeFactory;

    @Test
    void build() {
        Optional<TransactionType> result = this.transactionTypeFactory.build("Waging");
        assertAll( "Transaction type created",
                () -> assertTrue(result.isPresent()),
                () -> assertNotNull(result.get().getId())
        );
    }
}