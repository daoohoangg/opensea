package com.example.nftservice.controller;

import com.example.nftservice.dto.request.CollectionRequest;
import com.example.nftservice.dto.response.ApiResponse;
import com.example.nftservice.entity.Collection;
import com.example.nftservice.service.CollectionService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class CollectionController {
    @Autowired
    CollectionService collectionService;

    @PostMapping("/create/collection")
    public ApiResponse<Map> creatNewCollection(@ModelAttribute CollectionRequest collection) throws Exception {
        var result = collectionService.createCollection(collection);
        return ApiResponse.<Map>builder()
                .result(result)
                .build();
    }

}
