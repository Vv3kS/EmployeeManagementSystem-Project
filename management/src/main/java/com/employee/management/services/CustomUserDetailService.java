package com.employee.management.services;

import com.employee.management.entities.Users;
import com.employee.management.repositories.UserRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailService.class) ;


    private UserRespository userRespository ;

    @Autowired
    public CustomUserDetailService(UserRespository userRespository){
        this.userRespository = userRespository ;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRespository.findByUsername(username).orElseThrow(()->{
            logger.error("user not found with username : {}" , username);
            return  new UsernameNotFoundException("user " +username+" not found");
        });

        logger.info("Successfully loaded user");

        return new User(
                user.getUsername(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase()))
        );
    }
}
