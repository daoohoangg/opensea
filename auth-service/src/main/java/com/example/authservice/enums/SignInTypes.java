package com.example.authservice.enums;

public enum SignInTypes {
    USER_NAME("Sign in by username"),
    GMAIL("Sign in with Gmail"),
    METAMASK("Sign in with MetaMask"),
    MOBILE_WALLET("Sign in with mobile wallet");

    private final String description;

    SignInTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
