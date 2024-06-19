package com.crio.learning_system.controllers;

import com.crio.learning_system.config.AppSecurityConfig;
import com.crio.learning_system.services.NumberService;
import org.apache.catalina.security.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest( controllers = NumberController.class)
@Import(AppSecurityConfig.class)
public class NumberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NumberService numberService;

    @Test
    void testGetFact() throws Exception {
        // Given
        int number = 42;
        String expectedFact = "Something about 42";

        // Mock the service behavior
        when(numberService.getFact(number)).thenReturn(expectedFact);

        // When/Then
        mockMvc.perform(MockMvcRequestBuilders.get("/hidden-feature/" + number)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedFact));
    }
}
