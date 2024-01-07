package com.auth.security.controller;

import com.auth.security.dto.Register;
import com.auth.security.dto.TokenG;
import com.auth.security.entity.User;
import com.auth.security.service.AuthService;
import com.auth.security.service.AuthServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")

public class AuthController {

    @Autowired
    AuthServiceImpl authService;

    @PostMapping("/register")
    public ResponseEntity<Register> Register(@RequestBody Register user){
            return ResponseEntity.ok(authService.register(user));
    }


    @PostMapping("/login")
    public ResponseEntity<String> getToken(@RequestBody TokenG tokenG){
        Map<String, Object> response = new HashMap<>();

            String token = authService.generateToken(tokenG);
            response.put("res", token);
            return convertToJsonResponse(response, HttpStatus.OK);

    }

    private ResponseEntity<String> convertToJsonResponse(Map<String, Object> response, HttpStatus status) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String responseBody = objectMapper.writeValueAsString(response);
            return ResponseEntity.status(status).body(responseBody);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"JSON processing error\"}");
        }
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
