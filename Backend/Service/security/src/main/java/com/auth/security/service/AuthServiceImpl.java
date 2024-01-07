package com.auth.security.service;

import com.auth.security.dto.Register;
import com.auth.security.dto.TokenG;
import com.auth.security.entity.User;
import com.auth.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Register register(Register user) {
        User user1=new User();
        user1.setNom(user.getNom());
        user1.setEmail(user.getEmail());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user1);

        return user;


    }

    @Override
    public String generateToken(TokenG tokenG) {

        String res;
        try{
            Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(tokenG.getEmail(),tokenG.getPassword()));
            if(authentication.isAuthenticated()){
                res= jwtService.generateToken(tokenG.getEmail());
            }else {
                throw   new RuntimeException("User invalid acces");
            }
        } catch (BadCredentialsException e){
            res="bad credential";
            System.out.println(e.getMessage());
        }

            return res;


    }

    @Override
    public void validateToken(String token) {
         jwtService.validateToken(token);
    }
}
