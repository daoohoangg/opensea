package com.example.aiservice.service;

import com.example.aiservice.dto.ChatRequest;

public interface IChatService {
    String chat(ChatRequest chatRequest);
}
