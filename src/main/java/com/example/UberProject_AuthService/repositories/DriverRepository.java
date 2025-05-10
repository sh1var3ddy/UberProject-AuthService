package com.example.UberProject_AuthService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.UberProject_EntityService.models.Driver;
public interface DriverRepository extends JpaRepository<Driver, Long> {
}
