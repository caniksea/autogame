package com.caniksea.poll.rankinteractive.autogame.entity.transaction;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@ToString @Getter @Builder @Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@IdClass(Transaction.TransactionIdentifier.class)
public class Transaction {
    @Id private String playerId, transactionId;
    private String transactionTypeId;
    private BigDecimal transactionAmount;
    private LocalDateTime dateTime;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PUBLIC) @Getter
    public static class TransactionIdentifier implements Serializable {
        private String playerId, transactionId;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TransactionIdentifier that = (TransactionIdentifier) o;
            return playerId.equals(that.playerId) && transactionId.equals(that.transactionId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(playerId, transactionId);
        }
    }
}
