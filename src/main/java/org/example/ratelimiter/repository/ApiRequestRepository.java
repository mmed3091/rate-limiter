package org.example.ratelimiter.repository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;

@Repository
public class ApiRequestRepository {


    private final JdbcTemplate template;

    @Autowired
    public ApiRequestRepository(JdbcTemplate template) {
        this.template = template;
    }


    @Transactional
    public int addRequest(String apiKey, LocalDateTime timestamp) {
        String sql = "INSERT INTO api_requests (api_key, \"timestamp\") VALUES (?, ?)";

        try{
            return template.update(sql, apiKey, timestamp);
        }
        catch (DataAccessException e) {
            System.err.println("Error inserting request: " + e.getMessage());
            return 0;
        }

    }



}
