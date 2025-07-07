package com.example.nftservice.controller;

import com.example.nftservice.service.impl.IPFSUploadService;
import com.example.nftservice.service.impl.NFTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/nft")
public class NFTController {

    @Autowired
    private IPFSUploadService ipfsUploadService;

    @Autowired
    private NFTService nftService;



}
