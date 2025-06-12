package com.example.nftservice.controller;

import com.example.nftservice.dto.request.CollectionRequest;
import com.example.nftservice.dto.response.ApiResponse;
import com.example.nftservice.entity.Collection;
import com.example.nftservice.service.CollectionService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollectionController {
    @Autowired
    CollectionService collectionService;

    @PostMapping("/create/collection")
    public ResponseEntity<ApiResponse> creatNewCollection(@RequestBody CollectionRequest collection){

        return null;
    }

}
