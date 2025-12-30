package org.example.ratelimiter.model;

import org.springframework.cglib.core.Local;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;



public class TokenBucket {

    private int capacity;
    private int refillRate;
    private long tokens;
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
     *
     * @return
     */
    public int handleRequest(long tokens) {

        // calculate time passed since last request in seconds
        Duration duration  = Duration.between(this.lastRequestTime, LocalDateTime.now());
        long timePassed = duration.getSeconds();

        // refill tokens
        this.tokens = Math.min(this.capacity, this.tokens + (timePassed * this.refillRate));

        // set new lastRequestTime
        this.lastRequestTime = LocalDateTime.now();

        // check if enough tokens to accept request
        if (this.tokens >= tokens) {
            this.tokens -= tokens;
            return 1; // accept
        } else {
            return -1; // reject
        }
    }


    public long getTokens() {
        return this.tokens;
    }

    public int getCapacity() {
        return this.capacity;
    }


}
