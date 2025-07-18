package com.example.nftservice.service;

import com.example.nftservice.dto.request.CollectionRequest;
import com.example.nftservice.dto.request.NFTRequest;

import java.util.Map;

public interface INFTService {
    Map<String, String> upload(NFTRequest nftRequest) throws Exception;

}
