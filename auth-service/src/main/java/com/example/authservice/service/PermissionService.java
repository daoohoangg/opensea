package com.example.authservice.service;

import com.example.authservice.dto.request.PermissionRequest;
import com.example.authservice.dto.request.RoleRequest;
import com.example.authservice.dto.response.PermissionResponse;
import com.example.authservice.dto.response.RoleResponse;

import java.util.List;

public interface PermissionService {
    PermissionResponse create(PermissionRequest request);
    List<PermissionResponse> getAll();
    void delete(String permission);
}
