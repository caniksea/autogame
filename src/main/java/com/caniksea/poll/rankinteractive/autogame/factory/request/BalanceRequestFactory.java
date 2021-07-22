package com.caniksea.poll.rankinteractive.autogame.factory.request;

import com.caniksea.poll.rankinteractive.autogame.entity.request.BalanceRequest;
import com.caniksea.poll.rankinteractive.autogame.helper.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BalanceRequestFactory {

    private StringHelper stringHelper;

    @Autowired public BalanceRequestFactory(StringHelper stringHelper) {
        this.stringHelper = stringHelper;
    }

    public Optional<BalanceRequest> build(String transactionId, String playerId) {
        BalanceRequest balanceRequest = null;
        if (!(this.stringHelper.isEmptyOrNull(transactionId) && this.stringHelper.isEmptyOrNull(playerId)))
            balanceRequest = new BalanceRequest(transactionId, playerId);
        return Optional.ofNullable(balanceRequest);
    }
}
