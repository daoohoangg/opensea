package com.example.nftservice.service;

import com.example.nftservice.repository.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionService {
    @Autowired
    CollectionRepository collectionRepository;
    @Autowired
    IPFSUploadService ipfsUploadService;

}
