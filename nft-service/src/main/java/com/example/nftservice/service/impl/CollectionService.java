package com.example.nftservice.service.impl;

import com.example.nftservice.dto.request.CollectionRequest;
import com.example.nftservice.dto.response.CollectionNameResponse;
import com.example.nftservice.repository.CollectionRepository;
import com.example.nftservice.service.ICollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CollectionService implements ICollectionService {
    @Autowired
    IPFSUploadService ipfsUploadService;
    @Autowired
    CollectionRepository collectionRepository;
    public Map<String,String> createCollection(CollectionRequest collectionRequest) throws Exception {
        var result = ipfsUploadService.upload(collectionRequest);
        return result;
    }

    public List<CollectionNameResponse> getAllCollectionName() {
        return collectionRepository.findAllCollectionNames();
    }

    @Override
    public Map<String, String> upload(CollectionRequest collectionRequest) throws Exception {
        return null;
    }
}
