package com.caniksea.poll.rankinteractive.autogame.repository.transaction;

import com.caniksea.poll.rankinteractive.autogame.config.TestConfig;
import com.caniksea.poll.rankinteractive.autogame.entity.request.TransactionRequest;
import com.caniksea.poll.rankinteractive.autogame.entity.transaction.Transaction;
import com.caniksea.poll.rankinteractive.autogame.factory.transaction.TransactionFactory;
import com.caniksea.poll.rankinteractive.autogame.helper.StringHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(TestConfig.class)
class TransactionRepositoryTest {

    @Autowired private TestEntityManager entityManager;
    @Autowired private TransactionRepository repository;
    @Autowired private StringHelper stringHelper;
    private TransactionFactory transactionFactory;
    private Transaction transaction;

    @BeforeEach void setup() {
        TransactionRequest request = new TransactionRequest("1", "1", BigDecimal.TEN);
        this.transactionFactory = new TransactionFactory(this.stringHelper);
        this.transaction = this.transactionFactory.build("hi", request).get();
        this.entityManager.persist(this.transaction);
        this.entityManager.flush();
    }

    @Test @Disabled void save() {
        Transaction result = this.repository.save(this.transaction);
        System.out.println(result);
        assertNotNull(result);
    }

    @Test void findById() {
        Transaction.TransactionIdentifier id = new Transaction.TransactionIdentifier(this.transaction.getPlayerId(), this.transaction.getTransactionId());
        Optional<Transaction> result = this.repository.findById(id);
        System.out.println(result);
        assertAll(
                () -> assertTrue(result.isPresent()),
                () -> assertSame(this.transaction, result.get())
        );

    }

}