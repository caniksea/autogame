package com.caniksea.poll.rankinteractive.autogame.service;

import com.caniksea.poll.rankinteractive.autogame.entity.misc.APIConstant;
import com.caniksea.poll.rankinteractive.autogame.entity.misc.PromoCheck;
import com.caniksea.poll.rankinteractive.autogame.entity.request.TransactionRequest;
import com.caniksea.poll.rankinteractive.autogame.entity.transaction.APIKey;
import com.caniksea.poll.rankinteractive.autogame.entity.transaction.Promotion;
import com.caniksea.poll.rankinteractive.autogame.entity.transaction.Transaction;
import com.caniksea.poll.rankinteractive.autogame.entity.transaction.TransactionType;
import com.caniksea.poll.rankinteractive.autogame.entity.user.Player;
import com.caniksea.poll.rankinteractive.autogame.entity.user.PlayerAccount;
import com.caniksea.poll.rankinteractive.autogame.entity.user.PlayerPromotion;
import com.caniksea.poll.rankinteractive.autogame.factory.transaction.APIKeyFactory;
import com.caniksea.poll.rankinteractive.autogame.factory.transaction.TransactionFactory;
import com.caniksea.poll.rankinteractive.autogame.factory.user.PlayerPromotionFactory;
import com.caniksea.poll.rankinteractive.autogame.helper.PasswordHelper;
import com.caniksea.poll.rankinteractive.autogame.helper.StringHelper;
import com.caniksea.poll.rankinteractive.autogame.repository.transaction.APIKeyRepository;
import com.caniksea.poll.rankinteractive.autogame.repository.transaction.PromotionRepository;
import com.caniksea.poll.rankinteractive.autogame.repository.transaction.TransactionRepository;
import com.caniksea.poll.rankinteractive.autogame.repository.transaction.TransactionTypeRepository;
import com.caniksea.poll.rankinteractive.autogame.repository.user.PlayerAccountRepository;
import com.caniksea.poll.rankinteractive.autogame.repository.user.PlayerPromotionRepository;
import com.caniksea.poll.rankinteractive.autogame.repository.user.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {


    private TransactionFactory transactionFactory;
    private APIKeyFactory apiKeyFactory;
    private PlayerPromotionFactory playerPromotionFactory;

    private PasswordHelper passwordHelper;
    private StringHelper stringHelper;

    private PlayerRepository playerRepository;
    private PlayerAccountRepository playerAccountRepository;
    private TransactionTypeRepository transactionTypeRepository;
    private TransactionRepository transactionRepository;
    private APIKeyRepository apiKeyRepository;
    private PromotionRepository promotionRepository;
    private PlayerPromotionRepository playerPromotionRepository;

    private Optional<Transaction> buildRecord(Transaction transaction, List<TransactionType> transactionTypes) {
        return transactionTypes.stream()
                .filter(tt -> tt.getId().equalsIgnoreCase(transaction.getTransactionTypeId()))
                .findFirst().map(d -> this.transactionFactory.addTransactionTypeLiteral(transaction, d));
    }

    @Autowired public GameService(APIKeyFactory apiKeyFactory, PlayerPromotionFactory playerPromotionFactory, PasswordHelper passwordHelper,
                                  StringHelper stringHelper, PlayerRepository playerRepository,
                                  PlayerAccountRepository playerAccountRepository,
                                  TransactionTypeRepository transactionTypeRepository,
                                  TransactionFactory transactionFactory,
                                  TransactionRepository transactionRepository, APIKeyRepository apiKeyRepository,
                                  PromotionRepository promotionRepository, PlayerPromotionRepository playerPromotionRepository) {
        this.apiKeyFactory = apiKeyFactory;
        this.playerPromotionFactory = playerPromotionFactory;
        this.passwordHelper = passwordHelper;
        this.stringHelper = stringHelper;
        this.playerRepository = playerRepository;
        this.playerAccountRepository = playerAccountRepository;
        this.transactionTypeRepository = transactionTypeRepository;
        this.transactionFactory = transactionFactory;
        this.transactionRepository = transactionRepository;
        this.apiKeyRepository = apiKeyRepository;
        this.promotionRepository = promotionRepository;
        this.playerPromotionRepository = playerPromotionRepository;
    }

    /**
     * find player by id
     * @param playerId
     * @return
     */
    public Optional<Player> findPlayerById(String playerId) {
        return this.playerRepository.findById(playerId);
    }

    /**
     * find player by username
     * @param playerUsername
     * @return
     */
    public Optional<Player> findPlayerByUsername(String playerUsername) {
        return this.playerRepository.findByUsernameIgnoreCase(playerUsername);
    }

    /**
     * find player account by player id
     * @param playerId
     * @return
     */
    public Optional<PlayerAccount> findPlayerAccountById(String playerId) {
        return this.playerAccountRepository.findById(playerId);
    }

    /**
     * Save/update player balance
     * @param playerAccount
     * @return
     */
    public PlayerAccount saveBalance(PlayerAccount playerAccount) {
        return this.playerAccountRepository.save(playerAccount);
    }

    /**
     * Save wager/win transaction
     * @param transactionRequest
     * @param operation
     * @return
     */
    public Transaction saveTransaction(TransactionRequest transactionRequest, String operation) {
        TransactionType transactionType = this.transactionTypeRepository.findByNameIgnoreCase(operation)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Transaction type NOT found!"));
        Transaction transaction = this.transactionFactory.build(transactionType.getId(), transactionRequest)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Could not create transaction!"));
        return this.transactionRepository.save(transaction);
    }

    /**
     * find transaction
     * @param transactionRequest
     * @return
     */
    public Optional<Transaction> findTransactionById(TransactionRequest transactionRequest) {
        Transaction.TransactionIdentifier id = new Transaction.TransactionIdentifier(transactionRequest.getPlayerId(), transactionRequest.getTransactionId());
        return this.transactionRepository.findById(id);
    }

    /**
     * fetch player's last 10 transactions
     * @param playerId
     * @return
     */
    public List<Transaction> getLastTenTransactionsForPlayer(String playerId) {
        List<TransactionType> transactionTypes = this.transactionTypeRepository.findAll();
        List<Transaction> transactions = this.transactionRepository.findTop10ByPlayerIdOrderByDateTimeDesc(playerId);
        transactions = transactions.stream().map(t -> buildRecord(t, transactionTypes))
                .flatMap(Optional::stream).collect(Collectors.toList());
        return transactions;
    }

    /**
     * save/update customer secret key
     * @param secret
     * @return
     */
    public String setup(String secret) {
        APIKey apiKey = this.apiKeyFactory.build(APIConstant.KEY_CS_SUPPORT.value, secret)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Key build unsuccessful!"));
        apiKey = this.apiKeyRepository.save(apiKey);
        return apiKey.getKeyType();
    }

    /**
     * verify password
     * @param password
     * @return
     */
    public boolean verifyPassword(String password) {
        APIKey apiKey = this.apiKeyRepository.findById(APIConstant.KEY_CS_SUPPORT.value)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Key NOT found!"));
        return this.passwordHelper.passwordEncoder().matches(password, apiKey.getValue());
    }

    /**
     * check if promo is valid/used
     * @param transactionRequest
     * @return
     */
    public PromoCheck checkForPromo(TransactionRequest transactionRequest) {
        String promoCode = this.stringHelper.setEmptyIfNull(transactionRequest.getPromoCode()).toLowerCase();
        if (this.stringHelper.isEmptyOrNull(promoCode))
            return new PromoCheck(false, true, "No promo code!");
        Promotion promotion = this.promotionRepository.findById(promoCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Promo code NOT found!"));
        LocalDate today = LocalDate.now();
        if (today.isBefore(promotion.getStartDate()) || today.isAfter(promotion.getEndDate()))
            return new PromoCheck(false, false, "Promo ended/not started!");
        List<PlayerPromotion> playerPromotions =
                this.playerPromotionRepository
                        .findByPlayerIdAndPromoCodeOrderByDateTimeDesc(transactionRequest.getPlayerId(), promoCode);
        if (playerPromotions.size() < promotion.getNumberOfTimes())
            return new PromoCheck(true, true, "Valid promo code!");
        return new PromoCheck(false, false, "Promo used/exhausted");
    }

    /**
     * Save promo usage record.
     * @param transactionRequest
     */
    public void savePromoUse(TransactionRequest transactionRequest) {
        PlayerPromotion playerPromotion =
                this.playerPromotionFactory.build(transactionRequest.getTransactionId(), transactionRequest.getPlayerId(), transactionRequest.getPromoCode())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Could not create player promotion record"));
        this.playerPromotionRepository.save(playerPromotion);
    }
}
