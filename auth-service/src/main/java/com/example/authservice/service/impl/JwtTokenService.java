package com.example.authservice.service.impl;

import com.example.authservice.service.JwtManagement;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class JwtTokenService implements JwtManagement {
    private final StringRedisTemplate redisTemplate;

    public JwtTokenService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void storeToken(String token, String username, long expirationSeconds) {
        String key = "JWT:" + token;
        redisTemplate.opsForValue().set(key, username, expirationSeconds, TimeUnit.HOURS);
    }
    @Override
    public void storeRefreshToken(String username, String token, long durationInSeconds) {
        String key = "refresh:" + username;
        redisTemplate.opsForValue().set(key, token, durationInSeconds, TimeUnit.SECONDS);
    }
    @Override
    public boolean isTokenValid(String token) {
        String key = "JWT:" + token;
        return redisTemplate.hasKey(key);
    }

    @Override
    public void revokeToken(String token) {
        String key = "JWT:" + token;
        redisTemplate.delete(key);
    }
    public long getExpirationMillis(JWTClaimsSet jwtClaimsSet) {
        return jwtClaimsSet.getExpirationTime().getTime();
    }

}
