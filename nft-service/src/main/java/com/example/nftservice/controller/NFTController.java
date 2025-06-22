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



}
