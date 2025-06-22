package com.example.nftservice.repository;

import com.example.nftservice.dto.response.CollectionNameDTO;
import com.example.nftservice.entity.Collection;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends MongoRepository<Collection, String> {
    @Aggregation(pipeline = {
            "{ $project: { id: 1, name: '$metadata.metadata.name' } }"
    })
    List<CollectionNameDTO> findAllCollectionNames();

}
