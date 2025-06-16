package com.example.nftservice.service;

import com.example.nftservice.dto.request.CollectionRequest;
import com.example.nftservice.entity.Collection;
import com.example.nftservice.repository.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CollectionService {
    @Autowired
    IPFSUploadService ipfsUploadService;

    public Map<String,String> createCollection(CollectionRequest collectionRequest) throws Exception {
        var result = ipfsUploadService.upload(collectionRequest);
        return result;
    }
}
