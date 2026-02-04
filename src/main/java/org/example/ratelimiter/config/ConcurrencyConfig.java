package org.example.ratelimiter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Semaphore;

@Configuration
public class ConcurrencyConfig {

    private static final int MAX_CONCURRENT = 3;

    @Bean
    public Semaphore semaphore() {
        return new Semaphore(MAX_CONCURRENT);
    }
}
