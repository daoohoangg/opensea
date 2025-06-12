package com.example.nftservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "nft-collections")
public class Collection {
    @Id
    private String id;
    private String name;
    private String description;
    private String imageUrl;      // IPFS URL hoặc link thường
    private String url;
    private String category;
    private String website;
    private String twitter;
    private String discord;
    private String creatorWallet;
}
