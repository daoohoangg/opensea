package com.example.authservice.service.impl;

import com.example.authservice.dto.request.AccountCreationRequest;
import com.example.authservice.dto.request.AccountUpdateRequest;
import com.example.authservice.dto.request.RegisterMetamaskRequest;
import com.example.authservice.dto.request.WalletConnectRequest;
import com.example.authservice.dto.response.AccountResponse;
import com.example.authservice.dto.response.AuthenticationResponse;
import com.example.authservice.entity.Account;
import com.example.authservice.entity.Role;
import com.example.authservice.enums.Roles;
import com.example.authservice.exception.AppException;
import com.example.authservice.exception.ErrorCode;
import com.example.authservice.mapper.AccountMapper;
import com.example.authservice.repository.AccountRepository;
import com.example.authservice.repository.RoleRepository;
import com.example.authservice.service.AccountService;
import com.example.authservice.service.AuthenticationService;
import com.example.authservice.service.VerifyServices;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccountServiceIpml implements AccountService{
    AccountRepository accountRepository;
    RoleRepository roleRepository;
    //    ProfileClient profileClient;
    AccountMapper accountMapper;
    AuthenticationServiceImpl authenticationService;
    VerifyServices verifyServices;

    private final String GOOGLE_CLIENT_ID = "10942482793-kuln1t6m8band0acdojiudnelr00h0ta.apps.googleusercontent.com";
    @NonFinal
    @Value("${jwt.access-token-duration}")
    protected long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refresh-token-duration}")
    protected long REFRESHABLE_DURATION;
    PasswordEncoder passwordEncoderBCrypt = new BCryptPasswordEncoder(10);

    public AccountResponse createAccount(AccountCreationRequest accountCreationRequest) {
        if (accountRepository.existsByUsername(accountCreationRequest.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);
        Account account = accountMapper.toAccount(accountCreationRequest);
        account.setPassword(passwordEncoderBCrypt.encode(accountCreationRequest.getPassword()));

        Role userRole = roleRepository.findByName(Roles.USER).orElseThrow();
        account.setRoles(Set.of(userRole));

        account = accountRepository.save(account);
        //thieu profile mapper
        return accountMapper.toAccountResponse(account);
    }

    public AuthenticationResponse createAccountByMetamask(RegisterMetamaskRequest registerMetamaskRequest) {
        Account account = accountRepository.findByWallet(registerMetamaskRequest.getAddress());
        if (account == null) {
            account = accountMapper.toAccount(registerMetamaskRequest);
            account.setWalletAddress(registerMetamaskRequest.getAddress());

            Role userRole = roleRepository.findByName(Roles.USER).orElseThrow();
            account.setRoles(Set.of(userRole));

            account = accountRepository.save(account);
            // login luon = vi
            //can generate JWT o buoc nay de luu thong tin dang nhap
        }

        var accessToken = authenticationService.generateToken(account,VALID_DURATION,"accessToken");
        var refreshToken = authenticationService.generateToken(account,REFRESHABLE_DURATION, "refreshToken");
        //thieu profile mapper
//        return accountMapper.toAccountResponse(account);
        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .walletAddress(account.getWalletAddress())
                .authenticated(true)
                .build();
    }

    //dang nhap bang gmail
    public AuthenticationResponse createAccountByGmail(Map<String, String> payload) {
        String idTokenString = payload.get("idToken");
        Account account = new Account();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
                .Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(GOOGLE_CLIENT_ID))
                .build();

        try {
            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken == null) {
                log.warn("Invalid ID Token: " + idTokenString);
                System.out.println("1");
            }
            if (idToken != null) {
                GoogleIdToken.Payload tokenPayload = idToken.getPayload();

                String email = tokenPayload.getEmail();
                String name = (String) tokenPayload.get("name");

                // TODO: kiểm tra user, tạo JWT, lưu DB nếu cần
                if( accountRepository.findByEmail(email)== null){
                    account.setEmail(email);
                    account.setUsername(name);

                    Role userRole = roleRepository.findByName(Roles.USER).orElseThrow();
                    account.setRoles(Set.of(userRole));

                    accountRepository.save(account);
                };
//                taojwt

                // TODO: trả về thông tin account phù hợp
//                return accountMapper.toAccountResponse(account); // hoặc trả về giá trị phù hợp

            }
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace(); // hoặc log.error(...)
        }
        var accessToken = authenticationService.generateToken(account,VALID_DURATION,"accessToken");
        var refreshToken = authenticationService.generateToken(account,REFRESHABLE_DURATION, "refreshToken");


        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * Handles login or registration via WalletConnect (mobile wallet login).
     * <p>
     * This method verifies the signature provided by the user (signed with their wallet),
     * and either finds an existing account by wallet address or creates a new one.
     * It then generates a JWT token for authentication.
     * </p>
     *
     * @param walletConnectRequest the request containing wallet address, signed message, and original message
     * @return an {@link AuthenticationResponse} containing the JWT token for the authenticated session
     *
     * @throws AppException if the wallet signature is invalid (i.e. address does not match the signature)
     */
    public AuthenticationResponse connectWalletMobile(WalletConnectRequest walletConnectRequest){
        boolean isValid = verifyServices.verify(walletConnectRequest.getAddress(), walletConnectRequest.getSignature(), walletConnectRequest.getMessage());
        if(!isValid){
            throw new AppException(ErrorCode.INVALID_KEY);
        }
        Account account = accountRepository.findByWallet(walletConnectRequest.getAddress());
        if (account == null) {
            account = accountMapper.toAccount(walletConnectRequest);
            account.setWalletAddress(walletConnectRequest.getAddress());

            Role userRole = roleRepository.findByName(Roles.USER).orElseThrow();
            account.setRoles(Set.of(userRole));

            account = accountRepository.save(account);

        }

        var accessToken = authenticationService.generateToken(account,VALID_DURATION,"accessToken");
        var refreshToken = authenticationService.generateToken(account,REFRESHABLE_DURATION, "refreshToken");

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    //    xem thong tin tai khoan
    public AccountResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        Account account = accountRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return accountMapper.toAccountResponse(account);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public AccountResponse updateUser(String userId, AccountUpdateRequest request) {
        Account account = accountRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        accountMapper.updateAccount(account, request);
        account.setPassword(passwordEncoderBCrypt.encode(request.getPassword()));

//        var roles = roleRepository.findAllById(request.getRoles());
//        account.setRoles(new HashSet<>(roles));

        return accountMapper.toAccountResponse(accountRepository.save(account));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(String userId) {
        accountRepository.deleteById(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<AccountResponse> getUsers() {
//        log.info("In method get Users");
        return accountRepository.findAll().stream().map(accountMapper::toAccountResponse).toList();
    }
//    @PreAuthorize("hasRole('ADMIN')" )
    @PreAuthorize("hasAuthority('USER_DELETE')")
    public AccountResponse getUser(String id) {
        return accountMapper.toAccountResponse(
                accountRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }
}
