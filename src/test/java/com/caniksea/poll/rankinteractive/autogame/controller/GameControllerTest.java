package com.caniksea.poll.rankinteractive.autogame.controller;

import com.caniksea.poll.rankinteractive.autogame.entity.request.TransactionRequest;
import com.caniksea.poll.rankinteractive.autogame.entity.user.PlayerAccount;
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
        Object transactionRequest = new TransactionRequest("001", "001", BigDecimal.TEN);
        ResponseEntity responseEntity = this.restTemplate.postForEntity(url, transactionRequest, String.class);
        System.out.println(responseEntity.getBody());
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    void win() {
    }

    @Test
    void lastTenTransactions() {
    }

    @Test
    void setUp() {
    }
}