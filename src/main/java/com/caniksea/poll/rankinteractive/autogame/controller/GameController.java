package com.caniksea.poll.rankinteractive.autogame.controller;

import com.caniksea.poll.rankinteractive.autogame.entity.misc.APIConstant;
import com.caniksea.poll.rankinteractive.autogame.entity.misc.PromoCheck;
import com.caniksea.poll.rankinteractive.autogame.entity.request.TransactionHistoryRequest;
import com.caniksea.poll.rankinteractive.autogame.entity.request.TransactionRequest;
import com.caniksea.poll.rankinteractive.autogame.entity.transaction.Transaction;
import com.caniksea.poll.rankinteractive.autogame.entity.user.Player;
import com.caniksea.poll.rankinteractive.autogame.entity.user.PlayerAccount;
import com.caniksea.poll.rankinteractive.autogame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/autogame/")
public class GameController {

    private GameService gameService;

    private PlayerAccount getPlayerAccount(String playerId) {
        this.gameService.findPlayerById(playerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Player NOT found!"));
        PlayerAccount playerAccount = this.gameService.findPlayerAccountById(playerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Player account NOT found!"));
        return playerAccount;
    }

    private PlayerAccount getPlayerAccount(TransactionRequest transactionRequest) {
        if (this.gameService.findTransactionById(transactionRequest).isPresent())
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Transaction already exist!");
        return getPlayerAccount(transactionRequest.getPlayerId());
    }

    @Autowired public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("balance/{playerId}")
    public ResponseEntity balance(@PathVariable String playerId) throws RuntimeException {
        PlayerAccount playerAccount = getPlayerAccount(playerId);
        return ResponseEntity.ok(playerAccount);
    }

    @PostMapping("withdraw")
    public ResponseEntity wager(@RequestBody TransactionRequest transactionRequest) {
        PlayerAccount playerAccount = getPlayerAccount(transactionRequest);
        PromoCheck promoCheck = this.gameService.checkForPromo(transactionRequest);
        if (!promoCheck.proceed()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, promoCheck.notice());
        if (promoCheck.hasPromo()) {
            this.gameService.savePromoUse(transactionRequest);
            this.gameService.saveTransaction(transactionRequest, APIConstant.OP_WITHDRAW.value);
            return ResponseEntity.ok(playerAccount);
        }
        if (playerAccount.getBalance().compareTo(transactionRequest.getAmount()) < 0)
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "Insufficient fund");
        BigDecimal balance = playerAccount.getBalance().subtract(transactionRequest.getAmount());
        this.gameService.saveTransaction(transactionRequest, APIConstant.OP_WITHDRAW.value);
        playerAccount = playerAccount.toBuilder().balance(balance).build();
        playerAccount = this.gameService.saveBalance(playerAccount);
        return ResponseEntity.ok(playerAccount);
    }

    @PostMapping("deposit")
    public ResponseEntity win(@RequestBody TransactionRequest transactionRequest) {
        PlayerAccount playerAccount = getPlayerAccount(transactionRequest);
        BigDecimal balance = playerAccount.getBalance().add(transactionRequest.getAmount());
        this.gameService.saveTransaction(transactionRequest, APIConstant.OP_DEPOSIT.value);
        playerAccount = playerAccount.toBuilder().balance(balance).build();
        playerAccount = this.gameService.saveBalance(playerAccount);
        return ResponseEntity.ok(playerAccount);
    }

    @PostMapping("player-history")
    public ResponseEntity lastTenTransactions(@RequestBody TransactionHistoryRequest historyRequest) {
        Player player = this.gameService.findPlayerByUsername(historyRequest.getPlayerUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Player username NOT found!"));
        boolean isMatch = this.gameService.verifyPassword(historyRequest.getPassword());
        if (!isMatch) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong credentials!");
        List<Transaction> lastTenTransactions = this.gameService.getLastTenTransactionsForPlayer(player.getId());
        return ResponseEntity.ok(lastTenTransactions);
    }

    @GetMapping("setup/support-key/{secret}")
    public ResponseEntity setUp(@PathVariable String secret) {
        String result = this.gameService.setup(secret);
        return ResponseEntity.status(HttpStatus.OK).body(String.format("%s created!", result));
    }
}
