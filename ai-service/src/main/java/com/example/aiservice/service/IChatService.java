package com.example.aiservice.service;

import com.example.aiservice.dto.ChatRequest;
import com.example.aiservice.dto.ChatResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IChatService {
    ChatResponse chat(ChatRequest chatRequest);
    ChatResponse chatWithImage(MultipartFile multipartFile, ChatRequest message);
}
