package com.caniksea.poll.rankinteractive.autogame.entity.transaction;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@ToString @Getter @Builder @Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionType {
    @Id private String id;
    private String name;
}
