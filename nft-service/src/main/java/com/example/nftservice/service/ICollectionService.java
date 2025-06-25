package com.example.nftservice.service;

import com.example.nftservice.dto.request.CollectionRequest;

import java.util.Map;

public interface ICollectionService {
    Map<String, String>  upload(CollectionRequest collectionRequest) throws Exception;
}
