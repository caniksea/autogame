package com.caniksea.poll.rankinteractive.autogame.repository.user;

import com.caniksea.poll.rankinteractive.autogame.entity.user.PlayerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerAccountRepository extends JpaRepository<PlayerAccount, String> {}
