package com.employee.management.controllers;

import com.employee.management.dto.AuthenticationRequest;
import com.employee.management.dto.AuthenticationResponse;
import com.employee.management.entities.Users;
import com.employee.management.repositories.UserRespository;
import com.employee.management.services.CustomUserDetailService;
import com.employee.management.services.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager ;

    @Autowired
    private CustomUserDetailService customUserDetailService ;

    @Autowired
    private UserRespository userRespository ;

    @Autowired
    private JwtUtils jwtUtils ;

    @Autowired
    private PasswordEncoder passwordEncoder ;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        Users saveuser = userRespository.save(user);
        return ResponseEntity.ok("User registered Successfully") ;

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        Users user = userRespository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtUtils.generateToken(user.getUsername() , user.getEmail(), user.getRole());
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }
}
