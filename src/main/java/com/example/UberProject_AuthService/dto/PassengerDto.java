package com.example.UberProject_AuthService.dto;

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
}
