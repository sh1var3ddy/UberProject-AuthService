package com.example.UberProject_AuthService.controllers;

import com.example.UberProject_AuthService.dto.PassengerDto;
import com.example.UberProject_AuthService.dto.PassengerSignupRequestDto;
import com.example.UberProject_AuthService.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
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
}
