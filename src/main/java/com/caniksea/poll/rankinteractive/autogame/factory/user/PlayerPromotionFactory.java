package com.caniksea.poll.rankinteractive.autogame.factory.user;

import com.caniksea.poll.rankinteractive.autogame.entity.user.PlayerPromotion;
import com.caniksea.poll.rankinteractive.autogame.helper.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class PlayerPromotionFactory {

    private StringHelper stringHelper;

    @Autowired public PlayerPromotionFactory(StringHelper stringHelper) {
        this.stringHelper = stringHelper;
    }

    public Optional<PlayerPromotion> build(String transactionId, String playerId, String promoCode) {
        PlayerPromotion entity = null;
        if (!(this.stringHelper.isEmptyOrNull(playerId) && this.stringHelper.isEmptyOrNull(promoCode)))
            entity = PlayerPromotion.builder()
                    .transactionId(transactionId)
                    .playerId(playerId).promoCode(promoCode)
                    .dateTime(LocalDateTime.now()).build();
        return Optional.ofNullable(entity);
    }
}
