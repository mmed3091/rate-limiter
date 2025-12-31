package org.example.ratelimiter.model;

import org.springframework.cglib.core.Local;

import java.time.Duration;
import java.time.LocalDateTime;


public class TokenBucket {

    private int capacity;
    private int refillRate;
    private int tokens;
    private LocalDateTime lastRequestTime;

    /**
     *
     * @param capacity total amount of tokens the bucket can hold
     * @param refillRate rate at which tokens should be refilled
     */
    public TokenBucket(int capacity, int refillRate) {

        if (capacity <= 0 || refillRate <= 0) throw new IllegalArgumentException("Capacity and refill rate must be positive!\n");

        this.capacity = capacity;
        this.refillRate = refillRate;
        this.tokens = capacity; // current number of tokens in the bucket
        this.lastRequestTime = LocalDateTime.now(); // time of the last request
    }
    /**
     * @param requestedTokens number of tokens needed
     * @return number of tokens granted
     */
    public int handleRequest(int requestedTokens) {

        // calculate time passed since last request in seconds
        Duration duration  = Duration.between(this.lastRequestTime, LocalDateTime.now());
        long timePassed = duration.getSeconds();

        // refill tokens
        this.tokens = (int) Math.min(this.capacity, this.tokens + (timePassed * this.refillRate));

        // set new lastRequestTime
        this.lastRequestTime = LocalDateTime.now();

        // decide how many tokens to grant
        int grantedTokens = Math.min(requestedTokens, this.tokens);
        this.tokens -= grantedTokens;
        return grantedTokens;
    }

    public long getTokens() {
        return this.tokens;
    }

    public int getCapacity() {
        return this.capacity;
    }


}
