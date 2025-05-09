package com.example.UberProject_AuthService.dto;

import com.example.UberProject_AuthService.models.Passenger;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassengerDto {
    private String id;
    private String email;
    private String name;
    private String phoneNumber;
    private String password;
    private Date createdAt;

    public static PassengerDto fromPassenger(Passenger passenger) {
        return PassengerDto
                .builder()
                .id(passenger.getId().toString())
                .password(passenger.getPassword())
                .phoneNumber(passenger.getPhoneNumber())
                .name(passenger.getName())
                .email(passenger.getEmail())
                .createdAt(passenger.getCreatedAt())
                .build();
    }

}
