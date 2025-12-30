package org.example.ratelimiter;

import org.example.ratelimiter.model.TokenBucket;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TokenBucketTest {


    @Test
    void tokenBucketConstructorTest() {

        TokenBucket tb = new TokenBucket(10, 5);
        assertEquals(10, tb.getTokens());
        assertEquals(10, tb.getCapacity());
    }

}
