package com.healthcareapp.patientdatamanagementservice.security;

import com.healthcareapp.patientdatamanagementservice.constants.AppConstants;
import com.healthcareapp.patientdatamanagementservice.utils.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(AppConstants.HEADER_STRING);
        if (header == null || !header.startsWith(AppConstants.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
        } else {
            UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(header);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
        }
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String header) {
        if (header != null) {
            String token = header.replace(AppConstants.TOKEN_PREFIX, "");
            String email = JWTUtils.hasTokenExpired(token) ? null : JWTUtils.extractUser(token);
            if (email != null) {
                String userRole = JWTUtils.extractUserRoles(token);
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(userRole));
                return new UsernamePasswordAuthenticationToken(email, null, authorities);
            }
            return null;
        }
        return null;
    }
}
