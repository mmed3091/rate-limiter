package org.example.ratelimiter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
public class ApiController {


    @GetMapping("/get_request")
    public ResponseEntity<String> getRequest() {
        return ResponseEntity.status(200).body("GET request accepted\n");
    }

    @PostMapping ("/post_request")
    public ResponseEntity<String> postRequest() {
        return ResponseEntity.status(201).body("POST request accepted\n");
    }



}
