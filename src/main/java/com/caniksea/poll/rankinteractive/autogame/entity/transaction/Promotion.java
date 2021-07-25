package com.caniksea.poll.rankinteractive.autogame.entity.transaction;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString @Entity @Builder(toBuilder = true) @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Promotion {
    @Id private String code;
    private String description;
    private int numberOfTimes;
    private LocalDate startDate, endDate;
    private LocalDateTime createdAt;
}
