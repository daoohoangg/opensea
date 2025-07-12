package com.example.authservice.controller;

import com.example.authservice.dto.response.AccountResponse;
import com.example.authservice.dto.response.ApiResponse;
import com.example.authservice.service.AccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/admin")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminController {
    AccountService accountService;

    @PostMapping("/get-users")
    public ApiResponse<List<AccountResponse>> getAllAcountInfor(){
        var result = accountService.getUsers();
        return ApiResponse.<List<AccountResponse>>builder()
                .result(result)
                .build();
    }

}
