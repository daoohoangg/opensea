package com.example.aiservice.controller;

import com.example.aiservice.dto.ChatRequest;
import com.example.aiservice.service.ChatService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }


    @PostMapping("/chat")
    public String chat(@RequestBody ChatRequest chatRequest) {
        return chatService.chat(chatRequest);
    }
    @PostMapping("/chat-with-image")
    public String chatWithImage(@RequestParam("file")MultipartFile multipartFile,
                                @RequestParam("message") String message){
        return chatService.chatWithImage(multipartFile, message);
    }
}
