package com.example.UberProject_AuthService.dto;

import lombok.*;
import org.hibernate.usertype.BaseUserTypeSupport;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassengerSignupRequestDto {
    private String email;
    private String password;
    private String phoneNumber;
    private String name;

}
