package com.example.aiservice.service;

import com.example.aiservice.dto.ChatRequest;
import com.example.aiservice.dto.ChatResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ChatService implements IChatService {
    private final ChatClient chatClient;
    public ChatService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    final SystemMessage systemMessage = new SystemMessage("""
                Im chat bot of OpenNFT
                Your are funny and always tell jokes
                """);
    @Override
    public ChatResponse chat(ChatRequest chatRequest) {
        UserMessage userMessage = new UserMessage(chatRequest.message());
        Prompt prompt = new Prompt(systemMessage, userMessage);
        return chatClient
                .prompt(prompt)
                .call()
                .entity(new ParameterizedTypeReference<ChatResponse>() {
                });
    }

    public ChatResponse chatWithImage(MultipartFile multipartFile, ChatRequest message){
        UserMessage userMessage = new UserMessage(message.message());
        Prompt prompt = new Prompt(systemMessage, userMessage);
        Media media = Media.builder()
                .mimeType(MimeTypeUtils
                        .parseMimeType(multipartFile.getContentType()))
                .data(multipartFile.getResource())
                .build();

        ChatOptions chatOptions = ChatOptions.builder()
                .temperature(1D)
                .build();

        return chatClient.prompt(prompt)
                .options(chatOptions)
                .system("Chat bot of OpenNFT website trade NFT")//prompt cach 2
                .user(promptUserSpec
                        -> promptUserSpec.media(media)
                        .text(message.message()))
                .call()
                .entity(new ParameterizedTypeReference<ChatResponse>() {
                });
    }
}
