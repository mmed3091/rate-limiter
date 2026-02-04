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

    public int logRejectedRequest(String apiKey, LocalDateTime timestamp) {
        return this.repository.addRequest(apiKey, timestamp);

        //TODO: handle error case from the addRequest method
    }

}
