package com.employee.management.repositories;

import com.employee.management.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRespository extends JpaRepository<Users , Long> {
    Optional<Users> findByUsername(String username) ;
}
