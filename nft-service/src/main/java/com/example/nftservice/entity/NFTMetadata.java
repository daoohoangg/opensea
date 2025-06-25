package com.example.nftservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "nftmetadata")
public class NFTMetadata {
    public String name;
    public String description;
    public String image;
    public int supply;
    public String category;
    public String contractAddress;
    public String ownerwalletAddress;
    public Map<String, String> traits;
}
