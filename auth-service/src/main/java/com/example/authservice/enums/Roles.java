package com.example.authservice.enums;

public enum Roles {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");
    private final String description;


    Roles(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
