package com.example.nftservice.service.impl;

import com.example.nftservice.dto.request.NFTRequest;
import com.example.nftservice.dto.request.WalletAddressRequest;
import com.example.nftservice.service.INFTService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NFTService implements INFTService {
    @Value("${alchemy.api-key}")
    private String apiKey;

    @Value("${alchemy.network}")
    private String network;

    private final RestTemplate restTemplate = new RestTemplate();

    public JsonNode getNFTsByOwner(WalletAddressRequest walletAddressRequest) {
        String url = String.format(
                "https://%s.g.alchemy.com/v2/%s/getNFTs?owner=%s",
                network, apiKey, walletAddressRequest.getAddress()
        );

        try {
            ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            // Log lỗi chi tiết từ Alchemy
            System.err.println("Alchemy Error Response: " + e.getResponseBodyAsString());
            throw new RuntimeException("Failed to fetch NFTs from Alchemy: " + e.getStatusCode());
        }
    }


    @Override
    public Map<String, String> upload(NFTRequest nftRequest) throws Exception {
        return null;
    }
}
