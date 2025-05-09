package com.example.UberProject_AuthService.filters;

import com.example.UberProject_AuthService.services.JwtService;
import com.example.UberProject_AuthService.services.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component

public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserDetailsServiceImpl userDetailsService;

    private final JwtService jwtService;

    public JwtAuthFilter(UserDetailsServiceImpl userDetailsService, JwtService jwtService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        if(request.getCookies() != null) {
            for(Cookie cookie : request.getCookies()) {
                if(cookie.getName().equals("JwtToken")) {
                    token = cookie.getValue();
                }
            }
        }
        System.out.println("doFilterInternal called");
        if(token == null) {
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("Incoming token: " + token);

        String email = jwtService.extractEmail(token);

        System.out.println("Incoming email: " + email);

        if(email != null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            if(jwtService.validateToken(token,userDetails.getUsername())){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,null);
                authenticationToken.setDetails(userDetails);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }else{
                System.out.println("Invalid token");
            }
        }else{
            System.out.println("no email found");
        }
        System.out.println("Forwarded");
        System.out.println("JwtAuthFilter called for request: " + request.getRequestURI());
        filterChain.doFilter(request, response);
    }
}
