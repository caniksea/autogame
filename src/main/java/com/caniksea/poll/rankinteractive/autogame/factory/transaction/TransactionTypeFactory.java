package com.caniksea.poll.rankinteractive.autogame.factory.transaction;

import com.caniksea.poll.rankinteractive.autogame.entity.transaction.TransactionType;
import com.caniksea.poll.rankinteractive.autogame.helper.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TransactionTypeFactory {

    private StringHelper stringHelper;

    @Autowired public TransactionTypeFactory(StringHelper stringHelper) {
        this.stringHelper = stringHelper;
    }

    public Optional<TransactionType> build(String name) {
        TransactionType transactionType = null;
        if (!this.stringHelper.isEmptyOrNull(name))
            transactionType = TransactionType.builder()
                    .id(this.stringHelper.generateId())
                    .name(name).build();
        return Optional.ofNullable(transactionType);
    }
}
