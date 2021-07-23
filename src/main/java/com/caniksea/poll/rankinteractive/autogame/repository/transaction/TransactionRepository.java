package com.caniksea.poll.rankinteractive.autogame.repository.transaction;

import com.caniksea.poll.rankinteractive.autogame.entity.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Transaction.TransactionIdentifier> {
    List<Transaction> findTop10ByPlayerIdOrderByDateTimeDesc(String playerId);
}
