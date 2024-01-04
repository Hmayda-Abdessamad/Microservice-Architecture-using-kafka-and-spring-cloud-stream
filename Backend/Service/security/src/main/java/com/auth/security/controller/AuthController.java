package com.auth.security.controller;

import com.auth.security.dto.Register;
import com.auth.security.dto.TokenG;
import com.auth.security.entity.User;
import com.auth.security.service.AuthService;
import com.auth.security.service.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthServiceImpl authService;

    @PostMapping("/register")
    public String Register(@RequestBody Register user){
        try {
            return authService.register(user);
        }catch (Exception e){
            return e.toString();
        }

    }


    @PostMapping("/login")
    public String getToken(@RequestBody TokenG tokenG){
        return authService.generateToken(tokenG);
    }



    @PostMapping("/validateToken")
    public String validateToken(@RequestParam("token") String token){
        String res;
        try{
           authService.validateToken(token);
           res="token valid";
        }catch (Exception e){
            res=e.getMessage();
        }

         return res;
    }
}
