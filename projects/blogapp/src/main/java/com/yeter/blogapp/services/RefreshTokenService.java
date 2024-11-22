package com.yeter.blogapp.services;

import com.yeter.blogapp.entities.RefreshToken;
import com.yeter.blogapp.repositories.RefreshTokenRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RefreshTokenService {
    private RefreshTokenRepository refreshTokenRepository;
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository){
        this.refreshTokenRepository=refreshTokenRepository;
    }
    public boolean isRefreshExpired(RefreshToken token){
        return token.getExpiryDate().before(new Date());
    }
}
