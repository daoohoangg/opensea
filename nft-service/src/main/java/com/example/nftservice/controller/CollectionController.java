package com.example.nftservice.controller;

import com.example.nftservice.dto.request.CollectionRequest;
import com.example.nftservice.dto.response.ApiResponse;
import com.example.nftservice.service.impl.CollectionService;
import com.example.nftservice.service.impl.NFTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/nft")
public class CollectionController {
    @Autowired
    CollectionService collectionService;
    @Autowired
    NFTService nftService;

    @GetMapping("/api/test")
    public String test(@RequestHeader(value="X-User-Id" , required= false) String userId,
                       @RequestHeader(value="X-User-Roles" , required= false) String roles) {
        return "Hello " + userId + " with roles " + roles;
    }
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

//    @PostMapping("/deploy")
//    public ResponseEntity<String> deploy(@RequestParam String uri) throws Exception {
//        NFTCollectionERC1155 contract = nftService.deployContract(uri);
//        return ResponseEntity.ok("Contract deployed at: " + contract.getContractAddress());
//    }

//    @PostMapping("/mint")
//    public ResponseEntity<String> mint(
//            @RequestParam String contract,
//            @RequestParam String to,
//            @RequestParam BigInteger tokenId,
//            @RequestParam BigInteger amount) throws Exception {
//        TransactionReceipt receipt = nftService.mint(contract, to, tokenId, amount);
//        return ResponseEntity.ok("Minted! Tx: " + receipt.getTransactionHash());
//    }
}
