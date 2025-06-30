package com.example.authservice.controller;

import com.example.authservice.dto.request.*;
import com.example.authservice.dto.response.AccountResponse;
import com.example.authservice.dto.response.ApiResponse;
import com.example.authservice.dto.response.AuthenticationResponse;
import com.example.authservice.dto.response.IntrospectResponse;
import com.example.authservice.service.AccountService;
import com.example.authservice.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    AccountService accountService;

    @PostMapping("/log-in")
    ApiResponse<AuthenticationResponse>authenticationLogin(@RequestBody AuthenticationRequest authenticationRequest){
        var result = authenticationService.authenticate(authenticationRequest);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest introspectRequest) throws JOSEException, ParseException {
        var result = authenticationService.introspect(introspectRequest);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }
    @PostMapping("/registration")
    ApiResponse<AccountResponse> createUser(@RequestBody @Valid AccountCreationRequest request) {
        return ApiResponse.<AccountResponse>builder()
                .result(accountService.createAccount(request))
                .build();
    }
    @PostMapping("/registrationbymetamask")
    ApiResponse<AuthenticationResponse> creatUser(@RequestBody @Valid RegisterMetamaskRequest request){
        AuthenticationResponse result = accountService.createAccountByMetamask(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }
    @PostMapping("/registrationbygmail")
    ApiResponse<AuthenticationResponse> creatUser(@RequestBody @Valid Map<String, String> payload){
        AuthenticationResponse result = accountService.createAccountByGmail(payload);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }
    @PostMapping("/registrationbywallet")
    ApiResponse<AuthenticationResponse> creatUser(@RequestBody WalletConnectRequest walletConnectRequest){
        AuthenticationResponse result = accountService.connectWalletMobile(walletConnectRequest);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }
    //revoke and create new jwt
    // dung trong truong hop bi lo jwt
    @PostMapping("/refresh")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody RefreshRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.refreshToken(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }
    // dang xuat xoa trong cookies
    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest request)
            throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder()
                .build();
    }
}
