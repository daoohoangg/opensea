package com.example.authservice.controller;

import com.example.authservice.dto.request.PermissionRequest;
import com.example.authservice.dto.response.ApiResponse;
import com.example.authservice.dto.response.PermissionResponse;
import com.example.authservice.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/permission")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @PostMapping
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest permissionRequest){
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.create(permissionRequest))
                .build();
    }
    @GetMapping
    ApiResponse<List<PermissionResponse>> getAll(){
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAll())
                .build();

    }
    @PostMapping("/delete")
    ApiResponse<Void> deletePermission(@RequestBody List<String> list){
        for (String str:
             list) {
            permissionService.delete(str);
        }
        return ApiResponse.<Void>builder().build();
    }
}
