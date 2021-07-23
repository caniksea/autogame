package com.caniksea.poll.rankinteractive.autogame.service;

import com.caniksea.poll.rankinteractive.autogame.config.TestConfig;
import com.caniksea.poll.rankinteractive.autogame.entity.user.Player;
import com.caniksea.poll.rankinteractive.autogame.entity.user.PlayerAccount;
import com.caniksea.poll.rankinteractive.autogame.factory.user.PlayerAccountFactory;
import com.caniksea.poll.rankinteractive.autogame.factory.user.PlayerFactory;
import com.caniksea.poll.rankinteractive.autogame.repository.user.PlayerAccountRepository;
import com.caniksea.poll.rankinteractive.autogame.repository.user.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@Import(TestConfig.class)
class GameServiceTest {

    @Autowired private PlayerFactory playerFactory;
    @Autowired private PlayerAccountFactory playerAccountFactory;

    @Mock private PlayerRepository playerRepository;
    @Mock private PlayerAccountRepository playerAccountRepository;

    @InjectMocks private GameService service;

    @Test void findPlayerById() {
        Player entity = this.playerFactory.build("helen", "paul", "tomas")
                .orElseThrow(NoSuchElementException::new);
        Mockito.when(this.playerRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        Player player = this.service.findPlayerById(entity.getId()).orElseThrow(NoSuchElementException::new);
        System.out.println(player);
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
    void findPlayerByUsername() {
        Player entity = this.playerFactory.build("test", "hello", "there")
                .orElseThrow(NoSuchElementException::new);
        Mockito.when(this.playerRepository.findByUsername("test")).thenReturn(Optional.of(entity));
        Player player = this.service.findPlayerByUsername(entity.getUsername())
                .orElseThrow(NoSuchElementException::new);
        assertAll(
                () -> assertNotNull(player),
                () -> assertSame(entity, player)
        );
    }
}