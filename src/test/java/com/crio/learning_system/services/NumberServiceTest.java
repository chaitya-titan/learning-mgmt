package com.crio.learning_system.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NumberServiceTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private NumberService numberService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFact() {
        int number = 5;
        String url = "http://numbersapi.com/" + number;
        String expectedResponse = "5 is the number of fingers on a hand.";

        // Mock the RestTemplate response
        when(restTemplate.getForObject(url, String.class)).thenReturn(expectedResponse);

        // Call the service method
        String actualResponse = numberService.getFact(number);

        // Validate the result
        assertNotNull(actualResponse, "The response should not be null");
        assertEquals(expectedResponse, actualResponse, "The response should match the expected value");

        // Verify the interaction with RestTemplate
        verify(restTemplate, times(1)).getForObject(url, String.class);
    }
}
