package com.example.nftservice.service.impl;

import com.example.nftservice.dto.request.CollectionRequest;
import com.example.nftservice.dto.request.NFTRequest;
import com.example.nftservice.entity.Collection;
import com.example.nftservice.entity.CollectionMetadata;
import com.example.nftservice.entity.NFTMetadata;
import com.example.nftservice.repository.CollectionRepository;
import com.example.nftservice.service.ICollectionService;
import com.example.nftservice.service.INFTService;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class IPFSUploadService implements ICollectionService, INFTService {

    @Value("${pinata.jwt}")
    private String pinataJwt;

    private final CollectionRepository collectionRepository;
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public IPFSUploadService(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }


    public Map<String, String> upload(NFTRequest nftRequest) throws Exception {
        // 1. Upload image to IPFS
        String imageHash = uploadFileToPinata(nftRequest.getImage());
        String imageIpfsUrl = "ipfs://" + imageHash;

        // 2. Create metadata
        NFTMetadata metadata = new NFTMetadata();
        metadata.name = nftRequest.getName();
        metadata.description = nftRequest.getDescription();
        metadata.image = imageIpfsUrl;

        if (nftRequest.getAttributesJson() != null && !nftRequest.getAttributesJson().isEmpty()) {
            Map<String, String>attrs = objectMapper.readValue(nftRequest.getAttributesJson(), Map.class);
            metadata.traits = attrs;
        }

        // 3. Upload metadata to Pinata
        String metadataHash = uploadJsonToPinata(metadata);
        String tokenUri = "ipfs://" + metadataHash;

        return Map.of(
                "tokenUri", tokenUri,
                "imageIpfs", imageIpfsUrl
        );
    }
    public Map<String, String> upload(CollectionRequest collectionRequest) throws Exception {
        // 1. Upload image to IPFS
        String imageHash = uploadFileToPinata(collectionRequest.getLogo());
        String imageIpfsUrl = "ipfs://" + imageHash;

        // 2. Create metadata
        CollectionMetadata metadata = new CollectionMetadata();
        metadata.name = collectionRequest.getName();
        metadata.blockchain = collectionRequest.getBlockchain();
        metadata.image = imageIpfsUrl;
        metadata.symbols = collectionRequest.getSymbols();
        metadata.contractAddress = collectionRequest.getContractAddress();
        metadata.ownerwalletAddress = collectionRequest.getOwnerwalletAddress();

        // 3. Upload metadata to Pinata
        String metadataHash = uploadJsonToPinata(metadata);
        String tokenUri = "ipfs://" + metadataHash;

        Collection collection = new Collection();
        collection.setImageUrl(imageIpfsUrl);
        collection.setMetadata(Map.of(
                "metadata", metadata
        ));

        collectionRepository.insert(collection);


        return Map.of(
                "tokenUri", tokenUri,
                "imageIpfs", imageIpfsUrl
        );
    }

    @Override
    public Map<String, String> createCollection(CollectionRequest collectionRequest) {
        return null;
    }

    private String uploadFileToPinata(MultipartFile file) throws Exception {
        File temp = convertToFile(file)/* throw new AppException(ErrorCode.INVALID_FILE)*/;
        MediaType mediaType = MediaType.parse("application/octet-stream");
        RequestBody fileBody = RequestBody.create(mediaType, temp);

        MultipartBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getOriginalFilename(), fileBody)
                .build();

        Request request = new Request.Builder()
                .url("https://api.pinata.cloud/pinning/pinFileToIPFS")
                .addHeader("Authorization", pinataJwt)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String json = Objects.requireNonNull(response.body()).string();
            Map<String, Object> res = objectMapper.readValue(json, Map.class);
            return (String) res.get("IpfsHash");
        }
    }

    private String uploadJsonToPinata(Object jsonObj) throws Exception {
        String jsonString = objectMapper.writeValueAsString(jsonObj);
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jsonString);

        Request request = new Request.Builder()
                .url("https://api.pinata.cloud/pinning/pinJSONToIPFS")
                .addHeader("Authorization", pinataJwt)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String json = Objects.requireNonNull(response.body()).string();
            Map<String, Object> res = objectMapper.readValue(json, Map.class);
            return (String) res.get("IpfsHash");
        }
    }

    private File convertToFile(MultipartFile file) throws Exception {
        File convFile = File.createTempFile("nft", Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        }
        return convFile;
    }
}
