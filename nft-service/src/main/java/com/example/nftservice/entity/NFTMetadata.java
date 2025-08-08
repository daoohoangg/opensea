package com.example.nftservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "nftmetadata")
public class NFTMetadata {
    private String tokenId;             // ID của NFT (chuỗi hoặc số)
    private String collectionAddress;   // địa chỉ contract của collection
    private String ownerAddress;        // địa chỉ ví hiện đang sở hữu
    private String name;                // tên NFT
    private String description;         // mô tả NFT
    private String imageUrl;            // link ảnh NFT (IPFS, HTTPS)
    private Map<String, Object> traits; // thuộc tính NFT (key-value)
    private Double price;               // giá (nếu có listing)
    private String currency;            // đơn vị giá (ETH, MATIC, USDT...)
    private Long mintedBlock;           // block number khi mint NFT
    private LocalDate mintedAt;              // thời gian mint
    private LocalDate updatedAt;
    private String status;
}
