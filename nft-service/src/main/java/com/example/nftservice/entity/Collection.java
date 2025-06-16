package com.example.nftservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "nft-collections")
public class Collection {
    @Id
    private String id;
    private String imageUrl;      // IPFS URL hoặc link thường
    @Field("metadata")
    private Map<String, CollectionMetadata> metadata;
}
