package org.example.ratelimiter.service;


import org.antlr.v4.runtime.Token;
import org.example.ratelimiter.model.TokenBucket;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.HashMap;

@Service
public class RateLimiterService {


    // in-memory mapping of clients to token buckets
    private final Map<String, TokenBucket> buckets = new HashMap<>();

    private final int capacity = 10;
    private final int refillRate = 5;

    /**
     * Attempts to acquire tokens from the bucket associated with the API Key.
     * If the bucket doesn't exist it creates a new one.
     *
     * @param apiKey identifier for the client
     * @param requestedTokens number of tokens requested by the client to consume
     * @return number of tokens granted
     */
    public int tryAcquireTokens(String apiKey, int requestedTokens) {
        // find TokenBucket associated with key, or create a new one if it doesn't exist
        TokenBucket bucket = buckets.computeIfAbsent(apiKey, key -> new TokenBucket(this.capacity, this.refillRate));
        // return the number of tokens granted
        return bucket.handleRequest(requestedTokens);

    }


}
