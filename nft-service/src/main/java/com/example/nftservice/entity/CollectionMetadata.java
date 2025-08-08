package com.example.nftservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "collection-metadata")
public class CollectionMetadata {
    private String contractAddress;
    private String name;
    private String symbol;
    private String description;
    private String imageUrl;
    private String creatorAddress;
    private String blockchain;
    private Long deployedBlock;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
