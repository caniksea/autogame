package com.caniksea.poll.rankinteractive.autogame.factory.transaction;

import com.caniksea.poll.rankinteractive.autogame.entity.request.TransactionRequest;
import com.caniksea.poll.rankinteractive.autogame.entity.transaction.Transaction;
import com.caniksea.poll.rankinteractive.autogame.entity.transaction.TransactionType;
import com.caniksea.poll.rankinteractive.autogame.helper.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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
                    .transactionId(transactionRequest.getTransactionId())
                    .playerId(transactionRequest.getPlayerId())
                    .transactionTypeId(transactionTypeId)
                    .transactionAmount(transactionRequest.getAmount())
                    .dateTime(LocalDateTime.now()).build();
        return Optional.ofNullable(transaction);
    }

    public Transaction addTransactionTypeLiteral(Transaction transaction, TransactionType transactionType) {
        return transaction.toBuilder().transactionType(transactionType.getName())
                .build();
    }
}
