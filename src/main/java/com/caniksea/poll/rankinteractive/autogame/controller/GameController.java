package com.caniksea.poll.rankinteractive.autogame.controller;

import com.caniksea.poll.rankinteractive.autogame.entity.APIConstant;
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

    @Autowired public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("balance/{playerId}")
    public ResponseEntity balance(@PathVariable String playerId) throws RuntimeException {
        this.gameService.findPlayerById(playerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Player NOT found!"));
        PlayerAccount playerAccount = this.gameService.findPlayerAccountById(playerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Player account NOT found!"));
        return ResponseEntity.ok(playerAccount);
    }

    @PostMapping("withdraw")
    public ResponseEntity wager(@RequestBody TransactionRequest transactionRequest) {
        this.gameService.findPlayerById(transactionRequest.playerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Player NOT found!"));
        PlayerAccount playerAccount = this.gameService.findPlayerAccountById(transactionRequest.playerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Player account NOT found!"));
        if (playerAccount.getBalance().compareTo(transactionRequest.amount()) < 0)
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "Insufficient fund");
        this.gameService.findTransactionById(transactionRequest)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Transaction already exist!"));
        BigDecimal balance = playerAccount.getBalance().subtract(transactionRequest.amount());
        this.gameService.saveTransaction(transactionRequest, APIConstant.OP_WITHDRAW.value);
        playerAccount = playerAccount.toBuilder().balance(balance).build();
        playerAccount = this.gameService.saveBalance(playerAccount);
        return ResponseEntity.ok(playerAccount);
    }

    @PostMapping("deposit")
    public ResponseEntity win(@RequestBody TransactionRequest transactionRequest) {
        this.gameService.findPlayerById(transactionRequest.playerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Player NOT found!"));
        PlayerAccount playerAccount = this.gameService.findPlayerAccountById(transactionRequest.playerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Player account NOT found!"));
        this.gameService.findTransactionById(transactionRequest)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Transaction already exist!"));
        BigDecimal balance = playerAccount.getBalance().add(transactionRequest.amount());
        this.gameService.saveTransaction(transactionRequest, APIConstant.OP_DEPOSIT.value);
        playerAccount = playerAccount.toBuilder().balance(balance).build();
        playerAccount = this.gameService.saveBalance(playerAccount);
        return ResponseEntity.ok(playerAccount);
    }

    @PostMapping("player-history")
    public ResponseEntity win(@RequestBody TransactionHistoryRequest historyRequest) {
        Player player = this.gameService.findPlayerByUsername(historyRequest.playerUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Player username NOT found!"));
        boolean isMatch = this.gameService.verifyPassword(historyRequest.password());
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
