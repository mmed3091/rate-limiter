package org.example.ratelimiter.service;

import org.example.ratelimiter.repository.ApiRequestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ApiRequestService {

    private final ApiRequestRepository repository;

    public ApiRequestService(ApiRequestRepository repository) {
        this.repository = repository;
    }

    public int addRequest(String apiKey, LocalDateTime timestamp) {

        int rows = this.repository.addRequest(apiKey, timestamp);
        System.out.println("Inserted rows: " + rows);
        return rows;
    }

}
