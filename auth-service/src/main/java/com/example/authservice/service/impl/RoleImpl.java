package com.example.authservice.service.impl;

import com.example.authservice.dto.request.RoleRequest;
import com.example.authservice.dto.response.RoleResponse;
import com.example.authservice.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleImpl implements RoleService {
    @Override
    public RoleResponse create(RoleRequest request) {
        return null;
    }

    @Override
    public List<RoleResponse> getAll() {
        return null;
    }

    @Override
    public void delete(String role) {

    }
}
