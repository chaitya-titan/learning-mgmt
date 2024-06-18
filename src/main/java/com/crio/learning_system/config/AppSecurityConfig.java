package com.crio.learning_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable) // TODO: Re-enable in production
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(HttpMethod.POST, "/exams").permitAll()
                        .requestMatchers(HttpMethod.GET, "/exams").permitAll()
                        .requestMatchers(HttpMethod.GET, "/exams/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/exams/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/student").permitAll()
                        .requestMatchers(HttpMethod.GET, "/student/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/student").permitAll()
                        .requestMatchers(HttpMethod.POST, "/student/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/student/{studentId}/register/{subjectId}")
                        .permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/student/*").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/student/{studentId}/register/{subjectId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/subject").permitAll()
                        .requestMatchers(HttpMethod.GET, "/subject/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/subject").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/subject/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/hidden-feature/**").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
