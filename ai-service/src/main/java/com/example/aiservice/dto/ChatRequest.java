package com.example.aiservice.dto;

public record ChatRequest(String message) {
    @Override
    public String message() {
        return message;
    }
}
