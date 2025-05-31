package com.example.nftservice.controller;

import com.example.nftservice.entity.NFT;
import com.example.nftservice.service.NFTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/nfts")
public class NFTController {
    @Autowired
    private NFTService nftService;

    @GetMapping
    public List<NFT> getNFT() {
        return nftService.getAllNFTs();
    }

    @GetMapping("/{name}")
    public NFT getNftByName(@PathVariable String name) {
        return nftService.getNftByName(name);
    }

    @GetMapping("/collection/{collectionName}")
    public NFT[] findByCollectionName(@PathVariable String collectionName) {
        return nftService.getNFTByCollectionName(collectionName);
    }

    @PostMapping
    public void saveNFT(@RequestBody NFT nft) {
        nftService.createNFT(nft);
    }

    @PutMapping("/{id}")
    public void updateNFT(@RequestBody NFT nft, @PathVariable String id) {
        nftService.updateNFT(id, nft);
    }

    @DeleteMapping("/{id}")
    public void deleteNFT(@PathVariable String id) {
        nftService.deleteNFT(id);
    }
}
