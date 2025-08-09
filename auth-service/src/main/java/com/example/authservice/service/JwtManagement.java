package com.example.authservice.service;

import org.springframework.data.redis.core.StringRedisTemplate;

public interface JwtManagement {
    void storeToken(String token, String username, long expirationSeconds);
    boolean isTokenValid(String token);
    void revokeToken(String token);
    void storeRefreshToken(String username, String token, long durationInSeconds);
}
