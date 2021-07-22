package com.caniksea.poll.rankinteractive.autogame.factory.transaction;

import com.caniksea.poll.rankinteractive.autogame.entity.request.TransactionRequest;
import com.caniksea.poll.rankinteractive.autogame.entity.transaction.Transaction;
import com.caniksea.poll.rankinteractive.autogame.helper.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TransactionFactory {

    private StringHelper stringHelper;

    @Autowired public TransactionFactory(StringHelper stringHelper) {
        this.stringHelper = stringHelper;
    }

    public Optional<Transaction> build(String transactionTypeId, TransactionRequest transactionRequest) {
        Transaction transaction = null;
        if (!this.stringHelper.isEmptyOrNull(transactionTypeId))
            transaction = Transaction.builder()
                    .transactionId(transactionRequest.transactionId())
                    .playerId(transactionRequest.playerId())
                    .transactionTypeId(transactionTypeId)
                    .transactionAmount(transactionRequest.amount()).build();
        return Optional.ofNullable(transaction);
    }
}
