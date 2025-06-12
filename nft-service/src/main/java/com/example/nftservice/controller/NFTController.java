package com.example.nftservice.controller;

import com.example.nftservice.dto.response.ApiResponse;
import com.example.nftservice.entity.NFT;
import com.example.nftservice.service.IPFSUploadService;
import com.example.nftservice.service.NFTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/nfts")
public class NFTController {

    @Autowired
    private IPFSUploadService ipfsUploadService;

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

    @PostMapping("/upload")
    public Map<String, String> uploadToIpfs(
            @RequestParam("image") MultipartFile image,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam(value = "attributes", required = false) String attributesJson
    ) throws Exception {
        return ipfsUploadService.upload(image, name, description, attributesJson);
    }

}
