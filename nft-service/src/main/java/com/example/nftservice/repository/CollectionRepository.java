package com.example.nftservice.repository;

import com.example.nftservice.entity.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionRepository extends MongoRepository<Collection, String> {
}
