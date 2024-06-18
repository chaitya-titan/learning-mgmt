package com.crio.learning_system.controllers;

import com.crio.learning_system.services.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberController {
    @Autowired
    private NumberService numberService;

    @GetMapping("/hidden-feature/{number}")
    public ResponseEntity<String> getFact(@PathVariable int number) {
        String fact = numberService.getFact(number);
        return ResponseEntity.ok(fact);
    }

}
