package com.example.nftservice.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollectionRequest {
    MultipartFile logo;
    String name;
    String symbols;
    String description;
    String category;
    String blockchain;
//    MultipartFile banner;
}
