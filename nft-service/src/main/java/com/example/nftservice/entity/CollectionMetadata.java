package com.example.nftservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "collection-metadata")
public class CollectionMetadata {
    public String name;
    public String description;
    public String image;
    public String blockchain;
}
