package com.caniksea.poll.rankinteractive.autogame.entity.misc;

public enum APIConstant {

    OP_WITHDRAW("Wager"), OP_DEPOSIT("Win"),
    KEY_CS_SUPPORT("Customer Support Key");

    public final String value;

    APIConstant(String value) {
        this.value = value;
    }
}
