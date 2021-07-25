package com.caniksea.poll.rankinteractive.autogame.repository.user;

import com.caniksea.poll.rankinteractive.autogame.entity.user.PlayerPromotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerPromotionRepository extends JpaRepository<PlayerPromotion, PlayerPromotion.PlayerPromotionId> {
    List<PlayerPromotion> findByPlayerIdAndPromoCodeOrderByDateTimeDesc(String playerId, String promoCode);
}