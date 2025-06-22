package com.example.nftservice.controller;

import com.example.nftservice.dto.request.CollectionRequest;
import com.example.nftservice.dto.response.ApiResponse;
import com.example.nftservice.entity.Collection;
import com.example.nftservice.entity.NFTCollectionERC1155;
import com.example.nftservice.service.CollectionService;
import com.example.nftservice.service.NFTService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class CollectionController {
    @Autowired
    CollectionService collectionService;
    @Autowired
    NFTService nftService;

    @GetMapping("/get-collections")
    public ApiResponse<List> getAllCollection(){
        var result = collectionService.getAllCollectionName();
        return ApiResponse.<List>builder()
                .result(result)
                .build();
    }
    @PostMapping("/create/collection")
    public ApiResponse<Map> creatNewCollection(@ModelAttribute CollectionRequest collection) throws Exception {
        var result = collectionService.createCollection(collection);
        return ApiResponse.<Map>builder()
                .result(result)
                .build();
    }

    @PostMapping("/deploy")
    public ResponseEntity<String> deploy(@RequestParam String uri) throws Exception {
        NFTCollectionERC1155 contract = nftService.deployContract(uri);
        return ResponseEntity.ok("Contract deployed at: " + contract.getContractAddress());
    }

    @PostMapping("/mint")
    public ResponseEntity<String> mint(
            @RequestParam String contract,
            @RequestParam String to,
            @RequestParam BigInteger tokenId,
            @RequestParam BigInteger amount) throws Exception {
        TransactionReceipt receipt = nftService.mint(contract, to, tokenId, amount);
        return ResponseEntity.ok("Minted! Tx: " + receipt.getTransactionHash());
    }
}
