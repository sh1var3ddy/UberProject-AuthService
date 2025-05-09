package com.example.UberProject_AuthService.helpers;

import com.example.UberProject_EntityService.models.Passenger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class AuthPassengerDetails extends Passenger implements UserDetails {
    private final String username;
    private final String password;
    public AuthPassengerDetails(Passenger passenger) {
        this.username = passenger.getEmail(); // username is unique identifier
        this.password = passenger.getPassword();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}
