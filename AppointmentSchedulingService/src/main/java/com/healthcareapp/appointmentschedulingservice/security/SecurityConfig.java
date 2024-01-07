package com.healthcareapp.appointmentschedulingservice.security;


import com.healthcareapp.appointmentschedulingservice.constants.AppConstants;
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
                            .requestMatchers(HttpMethod.POST, AppConstants.SCHEDULE_CREATE).hasAuthority("DOCTOR")
                            .requestMatchers(HttpMethod.PUT, AppConstants.SCHEDULE_UPDATE).hasAuthority("DOCTOR")
                            .requestMatchers(HttpMethod.PUT, AppConstants.ONGOING_SCHEDULE_CHANGE).hasAuthority("DOCTOR")
                            .requestMatchers(HttpMethod.PUT, AppConstants.OVER_STATUS_CHANGE).hasAuthority("DOCTOR")
                            .requestMatchers(HttpMethod.PUT, AppConstants.APPOINTMENT_HAPPENED_CHANGE).hasAuthority("DOCTOR")
                            .requestMatchers(HttpMethod.DELETE, AppConstants.SCHEDULE_CANCEL).hasAuthority("DOCTOR")
                            .requestMatchers(HttpMethod.DELETE, AppConstants.APPOINTMENT_CANCEL).hasAuthority("PATIENT")
                            .requestMatchers(HttpMethod.POST, AppConstants.APPOINTMENT_ADD).hasAuthority("PATIENT")
                            .anyRequest().authenticated();
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
