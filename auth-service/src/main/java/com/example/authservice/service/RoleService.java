package com.example.authservice.service;

import com.example.authservice.dto.request.RoleRequest;
import com.example.authservice.dto.response.RoleResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface RoleService {
    RoleResponse create(RoleRequest request);
    List<RoleResponse> getAll();
    void delete(String role);
}
