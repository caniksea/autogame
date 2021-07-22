package com.caniksea.poll.rankinteractive.autogame.entity.user;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Getter @ToString @Builder @Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerAccount {
    @Id private String playerId;
    private BigDecimal balance;
}
