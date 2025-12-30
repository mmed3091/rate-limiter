package org.example.ratelimiter;

import org.example.ratelimiter.model.TokenBucket;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class TokenBucketTest {


    @Test
    void constructorTest() {
        TokenBucket tb = new TokenBucket(10, 5);
        assertEquals(10, tb.getTokens());
        assertEquals(10, tb.getCapacity());
    }

    @Test
    void constructorInvalidCapacityAndRefillRateTest() {
        assertThrows(IllegalArgumentException.class, () ->
                new TokenBucket(0, 0));
    }

    @Test
    void constructorNegativeRefillRateTest() {
        assertThrows(IllegalArgumentException.class, () ->
                new TokenBucket(10, -1));
    }

    @Test
    void constructorInvalidCapacityTest() {
        assertThrows(IllegalArgumentException.class, () ->
                new TokenBucket(0, 5));
    }

    @Test
    void constructorNegativeCapacityTest() {
        assertThrows(IllegalArgumentException.class, () ->
                new TokenBucket(-1, 5));
    }

    @Test
    void constructorInvalidRefillRateTest() {
        assertThrows(IllegalArgumentException.class, () ->
                new TokenBucket(10, 0));
    }

    @Test
    void handleRequestTokenNumbersTest() {
        TokenBucket tb = new TokenBucket(10, 5);
        tb.handleRequest(10);
        assertEquals(0, tb.getTokens());
    }

    @Test
    void handleRequestTokenNumbersLoopTest() {
        TokenBucket tb = new TokenBucket(10, 5);
        for(int i = 0; i < 10; i++) {
            tb.handleRequest(1);
        }
        assertEquals(0, tb.getTokens());
    }

    @Test
    void handleRequestAcceptTest() {
        TokenBucket tb = new TokenBucket(10, 5);
        assertEquals(1, tb.handleRequest(10));
    }

    @Test
    void handleRequestAcceptLoopTest() {
        TokenBucket tb = new TokenBucket(10, 5);
        for(int i = 0; i < 10; i++) {
            assertEquals(1, tb.handleRequest(1));
        }
    }

    @Test
    void handleRequestRejectTest() {
        TokenBucket tb = new TokenBucket(10, 5);
        assertEquals(-1, tb.handleRequest(11));
    }

    @Test
    void handleRequestRejectLoopTest() {
        TokenBucket tb = new TokenBucket(10, 5);
        for(int i = 0; i < 10; i++) {
            tb.handleRequest(1);
        }
        assertEquals(-1, tb.handleRequest(11));
    }

    @Test
    void handleRequestAcceptAndRejectTest() {
        TokenBucket tb = new TokenBucket(10, 5);
        assertEquals(1, tb.handleRequest(10));
        assertEquals(-1, tb.handleRequest(1));
    }

    @Test
    void handleRequestRejectAndAcceptTest() {
        TokenBucket tb = new TokenBucket(10, 5);
        assertEquals(-1, tb.handleRequest(11));
        assertEquals(1, tb.handleRequest(10));
    }














}
