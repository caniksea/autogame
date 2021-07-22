package com.caniksea.poll.rankinteractive.autogame.factory.transaction;

import com.caniksea.poll.rankinteractive.autogame.config.TestConfig;
import com.caniksea.poll.rankinteractive.autogame.entity.request.TransactionRequest;
import com.caniksea.poll.rankinteractive.autogame.entity.transaction.Transaction;
import com.caniksea.poll.rankinteractive.autogame.helper.StringHelper;
import org.junit.jupiter.api.BeforeEach;
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
class TransactionFactoryTest {

    @Autowired private StringHelper stringHelper;
    private TransactionRequest transactionRequest;
    private TransactionFactory factory;

    @BeforeEach void setup() {
        this.transactionRequest = new TransactionRequest("01", "01", BigDecimal.TEN);
        this.factory = new TransactionFactory(this.stringHelper);
    }

    @Test
    void build() {
        Optional<Transaction> transaction = this.factory.build("2", this.transactionRequest);
        System.out.println(transaction);
        assertNotNull(transaction.get());
    }
}