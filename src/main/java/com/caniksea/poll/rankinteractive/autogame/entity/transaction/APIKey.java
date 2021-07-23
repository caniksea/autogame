package com.caniksea.poll.rankinteractive.autogame.entity.transaction;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity @Builder @Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class APIKey {
    @Id private String keyType;
    private String value;
}
