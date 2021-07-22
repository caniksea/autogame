package com.caniksea.poll.rankinteractive.autogame.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringHelperTest {

    private StringHelper stringHelper;

    @BeforeEach void setup() {
        this.stringHelper = new StringHelper();
    }

    @Test
    void isEmptyOrNull() {
        String test = "";
        boolean result = this.stringHelper.isEmptyOrNull(test);
        assertTrue(result);
        test = "has string";
        result = this.stringHelper.isEmptyOrNull(test);
        assertFalse(result);
    }

    @Test
    void setEmptyIfNull() {
        String test = "";
        String result = this.stringHelper.setEmptyIfNull(test);
        assertEquals(0, result.length());
    }

    @Test void generateId() {
        String result = this.stringHelper.generateId();
        System.out.println(result);
        assertNotNull(result);
    }
}