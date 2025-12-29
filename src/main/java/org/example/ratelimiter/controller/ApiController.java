package org.example.ratelimiter.controller;

import jakarta.annotation.PostConstruct;
import org.example.ratelimiter.service.ApiRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;


@RestController
public class ApiController {

    private final ApiRequestService service;

//    @PostMapping("/request")
//    public ResponseEntity<String> handleRequest(@RequestHeader("X-API-KEY") String apiKey) {
//
////        check if api key is stored in database
////        fail
//
////        success
//        return ResponseEntity.ok("Request Successful!\n");
//
//
//    }
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void checkConnection() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Connected to database: " + connection.getCatalog());
            System.out.println("Connected as user: " + connection.getMetaData().getUserName());
        }
    }



    public ApiController(ApiRequestService service){
        this.service = service;
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertRequest(@RequestHeader("X-API-KEY") String apiKey) {

        LocalDateTime currentTimestamp = LocalDateTime.now();
        int result = this.service.addRequest(apiKey, currentTimestamp);

        // Error
        if (result > 0) {
            return ResponseEntity.status(201).body("Request added successfully\n");
            // Success
        } else {
            return ResponseEntity.status(500).body("Request adding failed\n");
        }


    }


}
