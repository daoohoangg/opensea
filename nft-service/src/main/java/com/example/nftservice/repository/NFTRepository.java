package com.example.nftservice.repository;

import com.example.nftservice.entity.NFT;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NFTRepository extends MongoRepository<NFT, String> {
    @Query("{name:'?0'}")
    NFT findNftByName(String name);

    @Query("{collectionName:'?0'}")
    NFT[] findNFTByCollectionName(String collectionName);
}
