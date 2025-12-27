package org.example.ratelimiter.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "api_requests")
public class ApiRequest {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // generate id automatically
    private Long id;
    private String apiKey;
    private LocalDateTime timestamp;

    public ApiRequest () {}

    public ApiRequest (String apiKey) {
        this.apiKey = apiKey;
    }

    public Long getId() {
        return this.id;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
