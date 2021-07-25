package com.caniksea.poll.rankinteractive.autogame.factory.transaction;

import com.caniksea.poll.rankinteractive.autogame.entity.transaction.Promotion;
import com.caniksea.poll.rankinteractive.autogame.helper.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class PromotionFactory {

    private StringHelper stringHelper;

    @Autowired public PromotionFactory(StringHelper stringHelper) {
        this.stringHelper = stringHelper;
    }

    public Optional<Promotion> build(String code, String description, int numberOfTimes, LocalDate startDate, LocalDate endDate) {
        Promotion promotion = null;
        numberOfTimes = numberOfTimes <= 0 ? 1 : numberOfTimes;
        if (!this.stringHelper.isEmptyOrNull(code) && endDate.isAfter(startDate))
            promotion = Promotion.builder()
                    .code(code)
                    .description(this.stringHelper.setEmptyIfNull(description))
                    .numberOfTimes(numberOfTimes).createdAt(LocalDateTime.now())
                    .startDate(startDate).endDate(endDate).build();
        return Optional.ofNullable(promotion);
    }
}
