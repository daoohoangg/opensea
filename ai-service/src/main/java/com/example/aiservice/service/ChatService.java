package com.example.aiservice.service;

import com.example.aiservice.dto.ChatRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ChatService implements IChatService {
    private final ChatClient chatClient;
    public ChatService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @Override
    public String chat(ChatRequest chatRequest) {
        SystemMessage systemMessage = new SystemMessage("""
                Im chat bot of OpenNFT
                Your are funny and always tell jokes
                """);
        UserMessage userMessage = new UserMessage(chatRequest.message());

        Prompt prompt = new Prompt(systemMessage, userMessage);
        return chatClient
                .prompt(prompt)
                .call()
                .content();
    }

    public String chatWithImage(MultipartFile multipartFile, String message){
        Media media = Media.builder()
                .mimeType(MimeTypeUtils
                        .parseMimeType(multipartFile.getContentType()))
                .data(multipartFile.getResource())
                .build();
        return chatClient.prompt()
                .system("chat bot of OpenNFT website trade NFT")
                .user(promptUserSpec
                        -> promptUserSpec.media(media)
                        .text(message))
                .call()
                .content();
    }
}
