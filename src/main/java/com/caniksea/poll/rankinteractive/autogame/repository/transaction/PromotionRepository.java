package com.caniksea.poll.rankinteractive.autogame.repository.transaction;

import com.caniksea.poll.rankinteractive.autogame.entity.transaction.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, String> {}
