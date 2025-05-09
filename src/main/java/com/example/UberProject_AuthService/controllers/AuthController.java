package com.example.UberProject_AuthService.controllers;

import com.example.UberProject_AuthService.dto.AuthRequestDto;
import com.example.UberProject_AuthService.dto.AuthResponseDto;
import com.example.UberProject_AuthService.dto.PassengerDto;
import com.example.UberProject_AuthService.dto.PassengerSignupRequestDto;
import com.example.UberProject_AuthService.services.AuthService;
import com.example.UberProject_AuthService.services.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Value("${cookie.expiry}")
    private int cookieExpiry;

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }
    @GetMapping
    public ResponseEntity<?> ping(){
        return new ResponseEntity<>("PONG", HttpStatus.OK);
    }

    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerDto> signUp(@RequestBody PassengerSignupRequestDto passengerSignupRequestDto) {
        PassengerDto response = this.authService.signupPassenger(passengerSignupRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/signin/passenger")
    public ResponseEntity<?> SignIn(@RequestBody AuthRequestDto authRequestDto, HttpServletResponse response) {
        System.out.println("request: " + authRequestDto.getEmail());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken((authRequestDto.getEmail()), authRequestDto.getPassword()));
        if(authentication.isAuthenticated()) {
            String jwtToken = this.jwtService.createToken(authRequestDto.getEmail());
            System.out.println(jwtToken);
            ResponseCookie cookie = ResponseCookie.from("JwtToken", jwtToken)
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(cookieExpiry)
                    .build();

            response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
            return new ResponseEntity<>(AuthResponseDto.builder().success(true).build(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Authentication Failed", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/validate")
    public ResponseEntity<?> Validate(HttpServletResponse response, HttpServletRequest request) {
        System.out.println("request validate called");
        for(Cookie cookie : request.getCookies()) {
            System.out.println(cookie.getName() + " " + cookie.getValue());
        }

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
