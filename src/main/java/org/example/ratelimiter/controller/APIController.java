package org.example.ratelimiter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;




@RestController
public class APIController {

    @GetMapping("/request")
    public ResponseEntity<String> handleRequest(@RequestHeader("X-API-KEY") String apiKey) {

//        check if api key is stored in database
//        fail

//        success
        return ResponseEntity.ok("Request Successful!\n");


    }


}
