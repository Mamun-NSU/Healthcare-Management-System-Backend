package com.healthcareapp.communityportalservice.security;


import com.healthcareapp.communityportalservice.constants.AppConstants;
import jakarta.ws.rs.HttpMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager)
            throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> {
                    auth

                            .requestMatchers(HttpMethod.POST, AppConstants.ADD_REVIEW).hasAuthority("PATIENT")
                            .requestMatchers(HttpMethod.GET, AppConstants.GET_ALL_REVIEWS).permitAll()
                            .requestMatchers(HttpMethod.DELETE, AppConstants.DELETE_REVIEW).hasAuthority("PATIENT")
                            .requestMatchers(HttpMethod.PUT, AppConstants.UPDATE_REVIEW).hasAuthority("PATIENT")
                            .requestMatchers(HttpMethod.POST, AppConstants.ADD_POST).authenticated()
                            .requestMatchers(HttpMethod.GET, AppConstants.GET_ALL_POST).authenticated()
                            .requestMatchers(HttpMethod.DELETE, AppConstants.DELETE_POST).hasAuthority("PATIENT")
                            .requestMatchers(HttpMethod.PUT, AppConstants.UPDATE_POST).hasAuthority("PATIENT")
                            .requestMatchers(HttpMethod.PUT, AppConstants.GET_BYID_POST).authenticated()
                            .requestMatchers(HttpMethod.GET, AppConstants.FIND_REVIEW_BY_PATIENT_ID).hasAuthority("PATIENT")
                            .requestMatchers(HttpMethod.POST, AppConstants.UPDATE_PROGRESS_CHECK).hasAuthority("PATIENT")
                            .requestMatchers(HttpMethod.GET, AppConstants.CHECK_PROGRESS).authenticated()
                            .requestMatchers(HttpMethod.GET, AppConstants.GET_PROGRESS_BY_ID).authenticated()
                            .anyRequest().permitAll();

                })
                .addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:5173");
        config.addAllowedHeader("*");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
