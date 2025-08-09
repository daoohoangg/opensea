package com.example.authservice.service;

import com.example.authservice.service.impl.JwtTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtManagementTest {

    private StringRedisTemplate redisTemplate;
    private ValueOperations<String, String> valueOps;
    private JwtTokenService jwtTokenService;

    @BeforeEach
    void setUp() {
        redisTemplate = mock(StringRedisTemplate.class);
        valueOps = mock(ValueOperations.class);
        when(redisTemplate.opsForValue()).thenReturn(valueOps);

        jwtTokenService = new JwtTokenService(redisTemplate);
    }

    @Test
    void storeToken_ShouldStoreWithCorrectKeyAndTTL() {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhdXRoLXNlcnZpY2UiLCJleHAiOjE3NTQ3MTkwOTAsImlhdCI6MTc1NDcxNTQ5MCwianRpIjoiODA2NTUyM2QtZGY2ZS00M2RkLWFhZGItZGIzMzhlOGY4MDYyIiwic2NvcGUiOiJST0xFX0FETUlOIFVTRVJfREVMRVRFIFVTRVJfUkVBRCJ9.ijRaGh5z6wo8LhwaJllM2Z5Tdx9yebsrnUBzkCkFcZ9xql0mF8SL0Kh8Xlptn-p_SpITsmFR7A02i4aaDQRqBQ";
        String username = "john";
        long expirationHours = 2L;

        jwtTokenService.storeToken(token, username, expirationHours);

        verify(valueOps, times(1))
                .set(eq("JWT:" + token), eq(username), eq(expirationHours), eq(TimeUnit.HOURS));
    }

    @Test
    void storeRefreshToken_ShouldStoreWithCorrectKeyAndTTL() {
        String username = "john";
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhdXRoLXNlcnZpY2UiLCJleHAiOjE3NTQ3MTkwOTAsImlhdCI6MTc1NDcxNTQ5MCwianRpIjoiODA2NTUyM2QtZGY2ZS00M2RkLWFhZGItZGIzMzhlOGY4MDYyIiwic2NvcGUiOiJST0xFX0FETUlOIFVTRVJfREVMRVRFIFVTRVJfUkVBRCJ9.ijRaGh5z6wo8LhwaJllM2Z5Tdx9yebsrnUBzkCkFcZ9xql0mF8SL0Kh8Xlptn-p_SpITsmFR7A02i4aaDQRqBQ";
        long ttlSeconds = 3600;

        jwtTokenService.storeRefreshToken(username, token, ttlSeconds);

        verify(valueOps, times(1))
                .set(eq("refresh:" + username), eq(token), eq(ttlSeconds), eq(TimeUnit.SECONDS));
    }

    @Test
    void isTokenValid_ShouldReturnTrueIfKeyExists() {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhdXRoLXNlcnZpY2UiLCJleHAiOjE3NTQ3MTkwOTAsImlhdCI6MTc1NDcxNTQ5MCwianRpIjoiODA2NTUyM2QtZGY2ZS00M2RkLWFhZGItZGIzMzhlOGY4MDYyIiwic2NvcGUiOiJST0xFX0FETUlOIFVTRVJfREVMRVRFIFVTRVJfUkVBRCJ9.ijRaGh5z6wo8LhwaJllM2Z5Tdx9yebsrnUBzkCkFcZ9xql0mF8SL0Kh8Xlptn-p_SpITsmFR7A02i4aaDQRqBQ";
        when(redisTemplate.hasKey("JWT:" + token)).thenReturn(true);

        boolean result = jwtTokenService.isTokenValid(token);

        assertTrue(result);
        verify(redisTemplate, times(1)).hasKey("JWT:" + token);
    }

    @Test
    void isTokenValid_ShouldReturnFalseIfKeyDoesNotExist() {
        String token = "abc123";
        when(redisTemplate.hasKey("JWT:" + token)).thenReturn(false);

        boolean result = jwtTokenService.isTokenValid(token);

        assertFalse(result);
        verify(redisTemplate, times(1)).hasKey("JWT:" + token);
    }

    @Test
    void revokeToken_ShouldDeleteKey() {
        String token = "abc123";

        jwtTokenService.revokeToken(token);

        verify(redisTemplate, times(1)).delete("JWT:" + token);
    }
//    @Test
//    void testGetExpirationMillis_Success() {
//        // Arrange
//        JWTClaimsSet mockClaimsSet = mock(JWTClaimsSet.class);
//        Date expirationDate = new Date(System.currentTimeMillis() + 3600000); // +1h
//        when(mockClaimsSet.getExpirationTime()).thenReturn(expirationDate);
//
//        JwtTokenService utils = new JwtTokenService();
//
//        // Act
//        long result = utils.getExpirationMillis(mockClaimsSet);
//
//        // Assert
//        assertEquals(expirationDate.getTime(), result);
//    }
//
//    @Test
//    void testGetExpirationMillis_NullExpiration() {
//        // Arrange
//        JWTClaimsSet mockClaimsSet = mock(JWTClaimsSet.class);
//        when(mockClaimsSet.getExpirationTime()).thenReturn(null);
//
//        JwtTokenService utils = new JwtTokenService();
//
//        // Act & Assert
//        assertThrows(NullPointerException.class, () -> utils.getExpirationMillis(mockClaimsSet));
//    }
}