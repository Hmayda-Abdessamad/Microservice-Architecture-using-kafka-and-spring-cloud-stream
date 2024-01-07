package com.auth.security.service;

import com.auth.security.entity.CustomUserDetails;
import com.auth.security.entity.User;
import com.auth.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

         Optional<User> user=userRepository.findByEmail(email);
         return user.map(CustomUserDetails::new).orElseThrow(()->new UsernameNotFoundException("user not found with name "+email));
    }
}
