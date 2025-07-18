package com.example.nftservice.controller;

import com.example.nftservice.dto.request.WalletAddressRequest;
import com.example.nftservice.dto.response.ApiResponse;
import com.example.nftservice.service.impl.IPFSUploadService;
import com.example.nftservice.service.impl.NFTService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/nft")
public class NFTController {

    @Autowired
    private IPFSUploadService ipfsUploadService;

    @Autowired
    private NFTService nftService;

    @PostMapping("/get-nfts")
    public ApiResponse<JsonNode> getNFTs(@RequestBody WalletAddressRequest owner) {
        JsonNode result = nftService.getNFTsByOwner(owner);
        return ApiResponse.<JsonNode>builder().result(result).build();
    }


}
