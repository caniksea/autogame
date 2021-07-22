package com.caniksea.poll.rankinteractive.autogame.entity.user;

import lombok.*;

import javax.persistence.*;

@ToString @Getter @Entity
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Player {
    @Id private String id;
    @Column(unique = true) private String username;
    private String firstName, lastName;
}
