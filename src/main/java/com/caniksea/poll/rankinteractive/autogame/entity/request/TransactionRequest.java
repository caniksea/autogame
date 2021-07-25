package com.caniksea.poll.rankinteractive.autogame.entity.request;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class TransactionRequest {
    private String transactionId, playerId;
    private BigDecimal amount;
    private String promoCode;
}
