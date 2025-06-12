package com.example.nftservice.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class CollectionRequest {
    MultipartFile image;
    String name;
    String description;
    String url;
    String category;
    String website;
    String twitter;
    String discord;
}
