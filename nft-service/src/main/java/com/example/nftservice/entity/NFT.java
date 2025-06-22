package com.example.nftservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "nfts")
public class NFT {
    @Id
    private String id;
    private String collectionName;
    private String imageName;
    private String name;
    private int supply;
    private String description;
    private String externalink;
    private Map<String,String> traits;

    public NFT(String collectionName, String imageName, String name, int supply, String description, String externalink, Map<String, String> traits) {
        this.collectionName = collectionName;
        this.imageName = imageName;
        this.name = name;
        this.supply = supply;
        this.description = description;
        this.externalink = externalink;
        this.traits = traits;
    }
}
