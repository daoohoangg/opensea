package com.example.aiservice.controller;

import com.example.aiservice.dto.ChatRequest;
import com.example.aiservice.dto.ChatResponse;
import com.example.aiservice.service.ChatService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/ai")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }


    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody ChatRequest chatRequest) {
        return chatService.chat(chatRequest);
    }
    @PostMapping("/chat-with-image")
    public ChatResponse chatWithImage(@RequestParam("file")MultipartFile multipartFile,
                                @RequestParam("message") ChatRequest message){
        return chatService.chatWithImage(multipartFile, message);
    }
}
