package com.yeter.blogapp.services;

import com.yeter.blogapp.entities.RefreshToken;
import com.yeter.blogapp.entities.User;
import com.yeter.blogapp.repositories.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Value("${refresh.token.expires.in}")
    Long expireSeconds;


    private RefreshTokenRepository refreshTokenRepository;
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository){
        this.refreshTokenRepository=refreshTokenRepository;
    }
    public String  createRefreshToken(User user){
        RefreshToken token=new RefreshToken();
        token.setUser(user);
        token.setExpiryDate(Date.from(Instant.now().plusSeconds(expireSeconds))); //apppropertiesde bir deyisende saxladim
        token.setToken(UUID.randomUUID().toString());
        refreshTokenRepository.save(token);
        return token.getToken();
    }
    public boolean isRefreshExpired(RefreshToken token){
        return token.getExpiryDate().before(new Date());
    }

    public RefreshToken getByUser(Long userId) {
     return  refreshTokenRepository.findByUserId(userId);
    }
}
