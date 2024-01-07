package com.auth.security.service;

import com.auth.security.dto.Register;
import com.auth.security.dto.TokenG;
import com.auth.security.entity.User;

public interface AuthService {

    public Register register(Register user);
    public String generateToken(TokenG name);
    public void validateToken(String token);
}
