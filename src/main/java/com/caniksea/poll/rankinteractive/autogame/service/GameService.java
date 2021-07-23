package com.caniksea.poll.rankinteractive.autogame.service;

import com.caniksea.poll.rankinteractive.autogame.entity.APIConstant;
import com.caniksea.poll.rankinteractive.autogame.entity.request.TransactionRequest;
import com.caniksea.poll.rankinteractive.autogame.entity.transaction.APIKey;
import com.caniksea.poll.rankinteractive.autogame.entity.transaction.Transaction;
import com.caniksea.poll.rankinteractive.autogame.entity.transaction.TransactionType;
import com.caniksea.poll.rankinteractive.autogame.entity.user.Player;
import com.caniksea.poll.rankinteractive.autogame.entity.user.PlayerAccount;
import com.caniksea.poll.rankinteractive.autogame.factory.transaction.APIKeyFactory;
import com.caniksea.poll.rankinteractive.autogame.factory.transaction.TransactionFactory;
import com.caniksea.poll.rankinteractive.autogame.helper.PasswordHelper;
import com.caniksea.poll.rankinteractive.autogame.repository.transaction.APIKeyRepository;
import com.caniksea.poll.rankinteractive.autogame.repository.transaction.TransactionRepository;
import com.caniksea.poll.rankinteractive.autogame.repository.transaction.TransactionTypeRepository;
import com.caniksea.poll.rankinteractive.autogame.repository.user.PlayerAccountRepository;
import com.caniksea.poll.rankinteractive.autogame.repository.user.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {


    private TransactionFactory transactionFactory;
    private APIKeyFactory apiKeyFactory;

    private PasswordHelper passwordHelper;

    private PlayerRepository playerRepository;
    private PlayerAccountRepository playerAccountRepository;
    private TransactionTypeRepository transactionTypeRepository;
    private TransactionRepository transactionRepository;
    private APIKeyRepository apiKeyRepository;

    @Autowired public GameService(APIKeyFactory apiKeyFactory, PasswordHelper passwordHelper,
                                  PlayerRepository playerRepository,
                                  PlayerAccountRepository playerAccountRepository,
                                  TransactionTypeRepository transactionTypeRepository,
                                  TransactionFactory transactionFactory,
                                  TransactionRepository transactionRepository, APIKeyRepository apiKeyRepository) {
        this.apiKeyFactory = apiKeyFactory;
        this.passwordHelper = passwordHelper;
        this.playerRepository = playerRepository;
        this.playerAccountRepository = playerAccountRepository;
        this.transactionTypeRepository = transactionTypeRepository;
        this.transactionFactory = transactionFactory;
        this.transactionRepository = transactionRepository;
        this.apiKeyRepository = apiKeyRepository;
    }

    public Optional<Player> findPlayerById(String playerId) {
        return this.playerRepository.findById(playerId);
    }

    public Optional<PlayerAccount> findPlayerAccountById(String playerId) {
        return this.playerAccountRepository.findById(playerId);
    }

    public PlayerAccount saveBalance(PlayerAccount playerAccount) {
        return this.playerAccountRepository.save(playerAccount);
    }

    public Transaction saveTransaction(TransactionRequest transactionRequest, String operation) {
        TransactionType transactionType = this.transactionTypeRepository.findByName(operation)
                .orElseThrow(() -> new RuntimeException("Transaction type NOT found!"));
        Transaction transaction = this.transactionFactory.build(transactionType.getId(), transactionRequest)
                .orElseThrow(() -> new RuntimeException("Could not create transaction!"));
        return this.transactionRepository.save(transaction);
    }

    public Optional<Transaction> findTransactionById(TransactionRequest transactionRequest) {
        Transaction.TransactionIdentifier id = new Transaction.TransactionIdentifier(transactionRequest.playerId(), transactionRequest.transactionId());
        return this.transactionRepository.findById(id);
    }

    public Optional<Player> findPlayerByUsername(String playerUsername) {
        return this.playerRepository.findByUsername(playerUsername);
    }

    public List<Transaction> getLastTenTransactionsForPlayer(String playerId) {
        return this.transactionRepository.findTop10ByPlayerIdOrderByDateTimeDesc(playerId);
    }

    public String setup(String secret) {
        APIKey apiKey = this.apiKeyFactory.build(APIConstant.KEY_CS_SUPPORT.value, secret)
                .orElseThrow(() -> new RuntimeException("Key build unsuccessful!"));
        apiKey = this.apiKeyRepository.save(apiKey);
        return apiKey.getKeyType();
    }

    public boolean verifyPassword(String password) {
        APIKey apiKey = this.apiKeyRepository.findById(APIConstant.KEY_CS_SUPPORT.value)
                .orElseThrow(() -> new RuntimeException("Key NOT found!"));
        return this.passwordHelper.passwordEncoder().matches(password, apiKey.getValue());
    }
}
