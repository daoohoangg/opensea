package com.example.nftservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CollectionResponse {
    String id;
    String collectionName;
    String authorName;
    float floorPrice;
    int itemQuantity;
    float totalValue;
    float listedPercent;
    List<NFTResponse> nftResponses;
}
