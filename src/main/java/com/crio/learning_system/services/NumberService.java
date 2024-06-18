package com.crio.learning_system.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NumberService {

    @Autowired
    private RestTemplate restTemplate;

    public String getFact(int number) {
        String url = numberUrl(number);
        return restTemplate.getForObject(url, String.class);
    }

    private String numberUrl(int number){
        return "http://numbersapi.com/" +
                number;
    }
}
