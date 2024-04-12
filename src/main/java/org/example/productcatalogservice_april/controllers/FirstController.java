package org.example.productcatalogservice_april.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok(
                "hello world"
        );
    }
}
