package com.caniksea.poll.rankinteractive.autogame.entity.request;

import lombok.Value;

@Value
public class TransactionHistoryRequest {
    private String playerUsername, password;
}