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

}
