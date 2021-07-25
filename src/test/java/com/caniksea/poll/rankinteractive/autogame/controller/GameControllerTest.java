package com.caniksea.poll.rankinteractive.autogame.controller;

import com.caniksea.poll.rankinteractive.autogame.entity.request.TransactionHistoryRequest;
import com.caniksea.poll.rankinteractive.autogame.entity.request.TransactionRequest;
import com.caniksea.poll.rankinteractive.autogame.entity.user.PlayerAccount;
import com.caniksea.poll.rankinteractive.autogame.helper.StringHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GameControllerTest {

    @Autowired private TestRestTemplate restTemplate;
    @Autowired private StringHelper stringHelper;
    @LocalServerPort private int port;
    private String baseURL;

    @BeforeEach void setup() {
        this.baseURL = "http://localhost:" + this.port + "/autogame/";
    }

    @Test
    void balance() {
        String url = this.baseURL + "balance/001";
        ResponseEntity responseEntity = this.restTemplate.getForEntity(url, PlayerAccount.class);
        System.out.println(responseEntity.getBody());
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    void wager() {
        String url = this.baseURL + "withdraw";
        TransactionRequest transactionRequest = new TransactionRequest(this.stringHelper.generateId(), "002", BigDecimal.TEN, "paper");
        ResponseEntity responseEntity = this.restTemplate.postForEntity(url, transactionRequest, PlayerAccount.class);
        System.out.println(responseEntity.getBody());
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    void win() {
        String url = this.baseURL + "deposit";
        TransactionRequest transactionRequest = new TransactionRequest(this.stringHelper.generateId(), "001", BigDecimal.TEN, null);
        ResponseEntity responseEntity = this.restTemplate.postForEntity(url, transactionRequest, PlayerAccount.class);
        System.out.println(responseEntity.getBody());
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    void lastTenTransactions() {
        String url = this.baseURL + "player-history";
        TransactionHistoryRequest historyRequest = new TransactionHistoryRequest("test-gamer", "swordfish");
        ResponseEntity responseEntity = this.restTemplate.postForEntity(url, historyRequest, String.class);
        System.out.println(responseEntity.getBody());
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    }
}