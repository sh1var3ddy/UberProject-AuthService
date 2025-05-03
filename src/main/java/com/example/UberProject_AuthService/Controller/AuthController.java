package com.example.UberProject_AuthService.Controller;

import com.example.UberProject_AuthService.dto.PassengerSignupRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @GetMapping
    public ResponseEntity<?> ping(){
        return new ResponseEntity<>("PONG", HttpStatus.OK);
    }
    @PostMapping("/signup/passenger")
    public ResponseEntity<?> signUp(@RequestBody PassengerSignupRequestDto passengerSignupRequestDto) {
        return new ResponseEntity<>("Sign Up Successful", HttpStatus.OK);
    }
}
