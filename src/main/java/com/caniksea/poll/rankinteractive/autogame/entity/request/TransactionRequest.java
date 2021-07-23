package com.caniksea.poll.rankinteractive.autogame.entity.request;

import java.io.Serializable;
import java.math.BigDecimal;

public record TransactionRequest(String transactionId, String playerId, BigDecimal amount) implements Serializable {}
