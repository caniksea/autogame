package com.caniksea.poll.rankinteractive.autogame.repository.transaction;

import com.caniksea.poll.rankinteractive.autogame.entity.transaction.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, String> {
    Optional<TransactionType> findByName(String name);
}
