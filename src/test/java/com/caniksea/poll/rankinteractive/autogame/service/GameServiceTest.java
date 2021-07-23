package com.caniksea.poll.rankinteractive.autogame.service;

import com.caniksea.poll.rankinteractive.autogame.config.TestConfig;
import com.caniksea.poll.rankinteractive.autogame.entity.APIConstant;
import com.caniksea.poll.rankinteractive.autogame.entity.request.TransactionRequest;
import com.caniksea.poll.rankinteractive.autogame.entity.transaction.Transaction;
import com.caniksea.poll.rankinteractive.autogame.entity.transaction.TransactionType;
import com.caniksea.poll.rankinteractive.autogame.entity.user.Player;
import com.caniksea.poll.rankinteractive.autogame.entity.user.PlayerAccount;
import com.caniksea.poll.rankinteractive.autogame.factory.transaction.APIKeyFactory;
import com.caniksea.poll.rankinteractive.autogame.factory.transaction.TransactionFactory;
import com.caniksea.poll.rankinteractive.autogame.factory.transaction.TransactionTypeFactory;
import com.caniksea.poll.rankinteractive.autogame.factory.user.PlayerAccountFactory;
import com.caniksea.poll.rankinteractive.autogame.factory.user.PlayerFactory;
import com.caniksea.poll.rankinteractive.autogame.helper.PasswordHelper;
import com.caniksea.poll.rankinteractive.autogame.helper.StringHelper;
import com.caniksea.poll.rankinteractive.autogame.repository.transaction.APIKeyRepository;
import com.caniksea.poll.rankinteractive.autogame.repository.transaction.TransactionRepository;
import com.caniksea.poll.rankinteractive.autogame.repository.transaction.TransactionTypeRepository;
import com.caniksea.poll.rankinteractive.autogame.repository.user.PlayerAccountRepository;
import com.caniksea.poll.rankinteractive.autogame.repository.user.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@Import(TestConfig.class)
class GameServiceTest {

    @Autowired private APIKeyFactory apiKeyFactory;
    @Autowired private PlayerFactory playerFactory;
    @Autowired private TransactionFactory transactionFactory;
    @Autowired private PlayerAccountFactory playerAccountFactory;
    @Autowired private TransactionTypeFactory transactionTypeFactory;

    @Autowired private PasswordHelper passwordHelper;

    @Mock private PlayerRepository playerRepository;
    @Mock private PlayerAccountRepository playerAccountRepository;
    @Mock private TransactionTypeRepository transactionTypeRepository;
    @Mock private TransactionRepository transactionRepository;
    @Mock private APIKeyRepository apiKeyRepository;

    private GameService service;

    @BeforeEach void testSetup() {
        assertNotNull(this.playerRepository);
        this.service = new GameService(this.apiKeyFactory, this.passwordHelper, this.playerRepository, this.playerAccountRepository,
                this.transactionTypeRepository, transactionFactory, transactionRepository, apiKeyRepository);
    }

    @Test void findPlayerById() {
        Player entity = this.playerFactory.build("helen", "paul", "tomas")
                .orElseThrow(NoSuchElementException::new);
        Mockito.when(this.playerRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        Player player = this.service.findPlayerById(entity.getId()).orElseThrow(NoSuchElementException::new);
        assertAll(
                () -> assertNotNull(player),
                () -> assertSame(entity, player)
        );
    }

    @Test
    void findPlayerAccountById() {
        PlayerAccount entity = this.playerAccountFactory.build("001", BigDecimal.TEN)
                .orElseThrow(NoSuchElementException::new);
        Mockito.when(this.playerAccountRepository.findById(entity.getPlayerId())).thenReturn(Optional.of(entity));
        PlayerAccount playerAccount = this.service.findPlayerAccountById(entity.getPlayerId()).orElseThrow(NoSuchElementException::new);
        assertAll(
                () -> assertNotNull(playerAccount),
                () -> assertSame(entity, playerAccount)
        );
    }

    @Test
    void saveBalance() {
        PlayerAccount entity = this.playerAccountFactory.build("001", BigDecimal.TEN)
                .orElseThrow(NoSuchElementException::new);
        Mockito.when(this.playerAccountRepository.save(entity)).thenReturn(entity);
        PlayerAccount playerAccount = this.service.saveBalance(entity);
        assertAll(
                () -> assertNotNull(playerAccount),
                () -> assertSame(entity, playerAccount)
        );
    }

    @Test
    /**
     * TODO: Complete
     */
    void saveTransaction() {
        TransactionRequest transactionRequest = new TransactionRequest("001", "112", BigDecimal.TEN);
        TransactionType transactionType = this.transactionTypeFactory.build(APIConstant.OP_WITHDRAW.value)
                .orElseThrow(NoSuchElementException::new);
        Mockito.when(this.transactionTypeRepository.findByName(APIConstant.OP_WITHDRAW.value)).thenReturn(Optional.of(transactionType));
        Transaction entity = this.transactionFactory.build(transactionType.getId(), transactionRequest)
                .orElseThrow(NoSuchElementException::new);
        Mockito.when(this.transactionRepository.save(entity)).thenReturn(entity);
        Transaction transaction = this.service.saveTransaction(transactionRequest, APIConstant.OP_WITHDRAW.value);
        assertAll(
                () -> assertNotNull(transaction),
                () -> assertSame(entity, transaction)
        );
    }

    @Test
    void findTransactionById() {
    }

    @Test
    void findPlayerByUsername() {
    }

    @Test
    void getLastTenTransactionsForPlayer() {
    }

    @Test
    void setup() {
    }

    @Test
    void verifyPassword() {
    }
}