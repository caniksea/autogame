package com.caniksea.poll.rankinteractive.autogame.entity.user;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Builder(toBuilder = true) @ToString @Getter @Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@IdClass(PlayerPromotion.PlayerPromotionId.class)
public class PlayerPromotion {
    @Id private String transactionId, playerId, promoCode;
    private LocalDateTime dateTime;


    public static class PlayerPromotionId implements Serializable {
        private String transactionId, playerId, promoCode;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PlayerPromotionId that = (PlayerPromotionId) o;
            return transactionId.equals(that.transactionId) && playerId.equals(that.playerId) && promoCode.equals(that.promoCode);
        }

        @Override
        public int hashCode() {
            return Objects.hash(transactionId, playerId, promoCode);
        }
    }
}
