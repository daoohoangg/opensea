package com.example.authservice.service;

import com.example.authservice.dto.request.AccountCreationRequest;
import com.example.authservice.dto.request.AccountUpdateRequest;
import com.example.authservice.dto.request.RegisterMetamaskRequest;
import com.example.authservice.dto.request.WalletConnectRequest;
import com.example.authservice.dto.response.AccountResponse;
import com.example.authservice.dto.response.AuthenticationResponse;

import java.util.List;
import java.util.Map;

public interface AccountService {
    AccountResponse createAccount(AccountCreationRequest accountCreationRequest);
    AuthenticationResponse createAccountByMetamask(RegisterMetamaskRequest registerMetamaskRequest);
    AuthenticationResponse createAccountByGmail(Map<String, String> payload);
    AuthenticationResponse connectWalletMobile(WalletConnectRequest walletConnectRequest);
    AccountResponse getMyInfo();
    //ADMIN_ROLE
    AccountResponse updateUser(String userId, AccountUpdateRequest request);
    List<AccountResponse> getUsers();
    AccountResponse getUser(String id);
}
