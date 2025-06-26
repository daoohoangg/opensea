package com.example.authservice.service;

import com.example.authservice.dto.request.AuthenticationRequest;
import com.example.authservice.dto.request.IntrospectRequest;
import com.example.authservice.dto.request.LogoutRequest;
import com.example.authservice.dto.request.RefreshRequest;
import com.example.authservice.dto.response.AuthenticationResponse;
import com.example.authservice.dto.response.IntrospectResponse;
import com.example.authservice.entity.Account;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    String generateToken(Account user);
    IntrospectResponse introspect(IntrospectRequest introspectRequest) throws JOSEException, ParseException;

    void logout(LogoutRequest logoutRequest) throws ParseException, JOSEException;
    AuthenticationResponse refreshToken(RefreshRequest refreshRequest) throws ParseException, JOSEException;
    SignedJWT verifyToken(String token) throws JOSEException, ParseException;
}
