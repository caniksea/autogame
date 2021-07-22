package com.caniksea.poll.rankinteractive.autogame.entity.transaction;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@ToString @Getter @Builder @Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@IdClass(Transaction.TransactionIdentifier.class)
public class Transaction {
    @Id private String playerId, transactionId;
    private String transactionTypeId;
    private BigDecimal transactionAmount;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PUBLIC) @Getter
    public static class TransactionIdentifier implements Serializable {
        private String playerId, transactionId;
    }
}
